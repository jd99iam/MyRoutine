package com.example.healthyclub.controller;

import com.example.healthyclub.dto.UserRequestDTO;
import com.example.healthyclub.dto.UserResponseDTO;
import com.example.healthyclub.entity.RoutineEntity;
import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.error.ErrorDTO;
import com.example.healthyclub.jwt.TokenProvider;
import com.example.healthyclub.service.RoutineService;
import com.example.healthyclub.service.UserService;
import com.example.healthyclub.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/auth")
public class Usercontroller {

    private final RoutineService routineService;
    private final UserService service;
    private final TokenProvider provider;
    private final PasswordEncoder encoder;

    @Value("${upload.path}")
    private String uploadRootPath;

    //회원가입하기
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestPart UserRequestDTO dto,
                                  @RequestPart(value = "profileImg", required = false) MultipartFile profileImg) throws IOException {

        log.info("/auth/join - {}",dto);
        //입력받은 userRequestDTO를 userEntity 타입으로 변환해준다
        UserEntity userEntity = new UserEntity(dto);
        try {
            if(profileImg != null){
                log.info("profileImg : {}",profileImg.getOriginalFilename());

                //1.서버에 이미지파일을 저장, 이미지를 서버에 업로드
                //1-a.파일 저장 위치를 지정하여 파일 객체에 포장
                String originalFilename = profileImg.getOriginalFilename();
                //1-a-1.파일명이 중복되지 않도록 변경
                String uploadFileName = UUID.randomUUID() + "_" + originalFilename;
                //1-a-2.압럳, 폴더를 날짜별로 생성
                String newUploadPath = FileUploadUtil.makeUploadDirectory(uploadRootPath);
                File uploadFile = new File(newUploadPath + File.separator + uploadFileName);
                //1-b. 파일을 해당 경로에 업로드
                profileImg.transferTo(uploadFile);

                String savePath
                        = newUploadPath.substring(uploadRootPath.length());

                userEntity.setProfileImg(savePath + File.separator + uploadFileName);

            }

            LocalDate date = LocalDate.now();
            userEntity.setJoinDate(date);
            userEntity.setUpdateDate(date);
            UserEntity user = service.create(userEntity);
            log.info("dto는 -{}",dto);
            log.info("profileImg는 -{}",profileImg);
            return ResponseEntity.ok().body(user);

        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }

    }

    //회원식별번호(id)를 @PathVariable로 보내주면 회원 정보를 보여주기
    @GetMapping("/show/{id}")
    public ResponseEntity<?> showOne(@PathVariable Long id){
        UserEntity entity = service.show(id);
        log.info("entity에 대한 정보 - {}", entity);
        String message = "해당 id과 일치하는 회원정보가 없습니다.";
        return (entity != null) ? ResponseEntity.ok().body(entity) : ResponseEntity.badRequest().body(new ErrorDTO(message));
    }

    //Userequestdto를 입력하면 정보를 바꿔주기
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody UserRequestDTO dto,@AuthenticationPrincipal String Id){

        if(Id.equals("anonymousUser")){
            String m = "접근 권한이 없습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(m));
        }

        Long userId = Long.parseLong(Id);
        if(!userId.equals(service.show(id).getId())){
            String m = "접근 권한이 없습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(m));
        }



        try {
            log.info("/auth/update- {}",dto);
            UserEntity user = service.show(id);
            log.info("dto pw -{}",dto.getPassword());
            if(encoder.matches(dto.getPassword(),user.getPassword())){
                user.setWeight(dto.getWeight());
                user.setHeight(dto.getHeight());
                user.setUpdateDate(LocalDate.now());
                service.update(user);
                return ResponseEntity.ok().body(user);
            }
            else{
                return ResponseEntity.badRequest().body(new ErrorDTO("비번 다름"));
            }

            //입력받은 userRequestDTO를 userEntity 타입으로 변환해준다
//            UserEntity userEntity = new UserEntity(dto);
//
//            //joinDate 집어넣기
//            LocalDate joinDate = service.show(id).getJoinDate();
//
//            //updatedate 갱신
//            LocalDate date = LocalDate.now();
//
//            userEntity.setJoinDate(joinDate);
//            userEntity.setUpdateDate(date);
//            userEntity.setId(id);
//            UserEntity user = service.update(userEntity);
//            log.info("@AuthenticationPrincipal String userId : {}",userId);
//            return ResponseEntity.ok().body(user);

        }catch(RuntimeException e){
            String message = "회원 update 실패";
            return ResponseEntity.badRequest().body(new ErrorDTO(message));
        }
    }

    @DeleteMapping("/delete/{id}/{pw}")
    public ResponseEntity<?> delete(@PathVariable String id, @PathVariable String pw, @AuthenticationPrincipal String Id){
        long longid = Long.parseLong(Id);
        UserEntity user = service.show(longid);

        if(id.equals(user.getUserId()) && encoder.matches(pw,user.getPassword())){

            // 해당 유저의 모든 루틴을 삭제해야함
            routineService.deleteAll(longid);


            // UserEntity delete = service.delete(longid);
            service.delete(longid);

            log.info("delete 잘 됨");
            return ResponseEntity.ok().body(null);
        }else{
            log.info("delete 실패");
            return ResponseEntity.badRequest().body(new ErrorDTO("아이디나 비번 틀림"));
        }
    }
    //로그인하기
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO dto){
        log.info("/auth/login POST - login info : {}",dto);
        try{
            UserEntity user = service.validateLogin(dto.getUserId(), dto.getPassword());
            //토큰 발행하기
            final String token = provider.create(user);
            UserResponseDTO userResponseDTO = new UserResponseDTO(user);
            userResponseDTO.setToken(token);

            return ResponseEntity.ok().body(userResponseDTO);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    //실명(name)에 해당되는 모든 user를 리턴
    @GetMapping("/showname/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name){
        log.info("/auth/show/name -{}",name);

        try{
            List<UserEntity> userByName = service.getUserByName(name);
            for(UserEntity x : userByName){
                Long id = x.getId();
                Integer getmyfollowers = service.getmyfollowers(id.intValue());
                if (getmyfollowers == null) getmyfollowers = 0;
                x.setFollower(getmyfollowers);
            }
            return ResponseEntity.ok().body(userByName);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    //운동타입에 해당하는 모든 user를 리턴
    @GetMapping("/showtype/{type}")
    public ResponseEntity<?> getUserByType(@PathVariable String type){
        log.info("/auth/show/type -{}",type);

        try{
            List<UserEntity> userByType = service.getUserByType(type);
            for(UserEntity x : userByType){
                Long id = x.getId();
                Integer getmyfollowers = service.getmyfollowers(id.intValue());
                if (getmyfollowers == null) getmyfollowers = 0;
                x.setFollower(getmyfollowers);
            }
            return ResponseEntity.ok().body(userByType);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/load-profile")
    public ResponseEntity<?> loadProfile(@AuthenticationPrincipal String id) throws IOException {
        log.info("/auth/load-profile GET - {}",id);
        long longId = Long.parseLong(id);
        ///해당 유저의 닉네임을 통해서 프로필 사진의 경로를 DB에서 조회
        //ex) /2023/01/07/ㄺㅎㄹ.파일명.확장자
        String profilePath = service.getProfileImg(longId);

        //ex) C:/profile_upload/2023/...
        String fullPath = uploadRootPath + File.separator + profilePath;

        //해당 경로를 파일 객체로 포장
        File targetFile = new File(fullPath);
        log.info("targetFile - {}",targetFile);
        //혹시 해당 파일이 존재하지 않으면 예외가 발생(FileNotFoundException)
        if(!targetFile.exists()) return ResponseEntity.notFound().build();

        //파일 데이터를 바이트배열로 포장 (blob 데이터)
        byte[] rawImageData = FileCopyUtils.copyToByteArray(targetFile);
        log.info("rawImageData - {}",rawImageData);
        //응답 헤더 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(FileUploadUtil.getMediaType(profilePath));

        return ResponseEntity.ok().headers(headers).body(rawImageData);
    }


}
