package com.example.healthyclub.controller;

import com.example.healthyclub.dto.RoutineDTO;
import com.example.healthyclub.entity.RoutineEntity;
import com.example.healthyclub.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;


    //루틴 생성
    @PostMapping("/routine/{userId}")
    public ResponseEntity<RoutineDTO> create(@RequestBody RoutineDTO routineDTO, @PathVariable Long userId, @AuthenticationPrincipal String tokenId){

        //TokenProvider에 setSubject에 인자로 전달할 때 인증을 위해 사용할 필드를 무조건 String 형으로 바꿔서 전달해야함
        //@AutehnticationPrincipal로 토큰을 받을 때도 String 형으로 받음
        //create를 호출할 때 Long 형으로 변환해서 인자로 전달함

        //서비스를 이용해 루틴 엔티티 생성 (dto 전달 -> 엔티티 생성 , DB 저장 -> dto 반환)
        RoutineDTO created = routineService.create(routineDTO, userId, Long.parseLong(tokenId));

        //응답코드와 함께 반환
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    //루틴 읽어오기 (유저의 모든 루틴을 읽어옴) - 루틴 읽어오기는 꼭 내 루틴만 읽어오는 것이 아님. 따라서 로그인만 하면 가능
    @GetMapping("/routine/{userId}")
    public ResponseEntity<List<RoutineDTO>> readAll(@PathVariable Long userId){

        //서비스를 이용해 리스트 받아옴
        List<RoutineDTO> routineDTOS = routineService.showAll(userId);

        return ResponseEntity.status(HttpStatus.OK).body(routineDTOS);
    }

    //루틴 읽어오기 (해당 id에 해당하는 루틴을 읽어옴)
    @GetMapping("/routine/{userId}/{routineId}")
    public ResponseEntity<RoutineDTO> readOne(@PathVariable Long userId, @PathVariable Long routineId){

        //서비스를 이용해 루틴 DTO 받아옴
        RoutineDTO routineDTO = routineService.show(userId, routineId);

        return ResponseEntity.status(HttpStatus.OK).body(routineDTO);
    }

    //루틴 수정
    @PatchMapping("/routine/{userId}/{routineId}")
    public ResponseEntity<RoutineDTO> update(@RequestBody RoutineDTO routineDTO, @PathVariable Long userId, @PathVariable Long routineId, @AuthenticationPrincipal String tokenId){

        //서비스에게 위임해서 수정
        RoutineDTO updated = routineService.update(routineDTO,userId,routineId,Long.parseLong(tokenId));

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //루틴 삭제
    @DeleteMapping("/routine/{userId}/{routineId}")
    public ResponseEntity<RoutineDTO> delete(@PathVariable Long userId, @PathVariable Long routineId, @AuthenticationPrincipal String tokenId){

        //서비스에게 위임해서 삭제
        RoutineDTO deleted = routineService.delete(userId,routineId,Long.parseLong(tokenId));

        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }


    //복사는 2가지 방식을 생각함
    // 1. 수정 없이 복사 : 아래 메소드 : routineId에 해당하는 루틴을 로그인한 사용자의 루틴으로 똑같이 복사
    // 2. 수정 하면서 복사 : 프론트에서 해당 루틴 수정 폼을 띄우고(show이용) 수정 폼에 입력한 후
    // 복사하기 누르면 create 이용해서 새로운 루틴을 만들어주는거랑 같음
    @GetMapping("/routine/copy/{routineId}")
    public ResponseEntity<RoutineDTO> copy(@PathVariable Long routineId, @AuthenticationPrincipal String tokenId){

        //서비스에게 위임해서 복사 : PK가 targetId인 유저의 PK가 routineId인 루틴을 PK가 tokenId인 유저의 루틴으로 생성
        RoutineDTO copied = routineService.copy(routineId,Long.parseLong(tokenId));

        return ResponseEntity.status(HttpStatus.OK).body(copied);
    }
}
