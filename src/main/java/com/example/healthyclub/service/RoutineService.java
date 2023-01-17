package com.example.healthyclub.service;

import com.example.healthyclub.dto.RoutineDTO;
import com.example.healthyclub.entity.RoutineEntity;
import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.repository.RoutineRepository;
import com.example.healthyclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;

    //Create
    @Transactional
    public RoutineDTO create(RoutineDTO routineDTO,Long userId){

        //유저리포지토리를 이용해서 유저 엔티티 생성 (이 유저의 루틴을 만들것임)
        UserEntity targetUser = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("RoutineService - create : 존재하지 않는 유저입니다"));

        //인자로 전달된 유저 id와 db에서 찾은 유저 id가 다른지 확인
        if (userId!=targetUser.getId()){
            throw new IllegalArgumentException("RoutineService - create : 잘못된 유저 접근입니다 : 유저 id가 일치하지 않습니다");
        }

        //루틴 엔티티 생성
        RoutineEntity target = RoutineEntity.toEntity(routineDTO,targetUser);

        //루틴 엔티티 DB에 저장
        RoutineEntity created = routineRepository.save(target);

        //엔티티를 dto로 변환해서 반환
        return RoutineDTO.toDTO(created);
    }

    //유저 아이디를 받아서 해당 유저의 루틴들을 List<RoutineDTO> 형태로 반환
    public List<RoutineDTO> showAll(Long userId) {

        return routineRepository.findByUserId(userId)
                .stream()
                .map(routine->RoutineDTO.toDTO(routine))
                .collect(Collectors.toList());
    }


    //유저 아이디랑 루틴 아이디를 받아서 해당 유저의 해당 루틴을 RoutineDTO 형태로 반환
    public RoutineDTO show(Long userId, Long routineId) {

        RoutineEntity found = routineRepository.findById(routineId)
                .orElseThrow(()->new IllegalArgumentException("RoutineService-show : 루틴을 찾을 수 없습니다"));

        if (found.getUser().getId()!=userId){
            throw new IllegalArgumentException("RoutineService-show : 잘못된 접근 : 로그인한 사용자의 루틴이 아닙니다");
        }

        //dto로 변환해서 반환
        return RoutineDTO.toDTO(found);
    }


    //수정 폼에 입력받은 dto, 유저 id, 루틴 id 받아서 수정해주고 dto 형태로 반환
    @Transactional
    public RoutineDTO update(RoutineDTO routineDTO, Long userId, Long routineId) {

        //수정할 루틴 DB에서 찾기
        RoutineEntity target = routineRepository.findById(routineId)
                .orElseThrow(()->new IllegalArgumentException("RoutineService-update : 루틴을 찾을 수 없습니다"));

        //예외 처리
        if (target.getUser().getId()!=userId){
            throw new IllegalArgumentException("RoutineService-update : 잘못된 접근 : 로그인한 사용자의 루틴이 아닙니다");
        }

        //루틴 수정
        target.patch(routineDTO);
        
        //DB에 적용하고 리턴
        return RoutineDTO.toDTO(routineRepository.save(target));
    }

    //루틴 삭제
    public RoutineDTO delete(Long userId, Long routineId) {
        //삭제할 루틴 DB에서 찾기
        RoutineEntity target = routineRepository.findById(routineId)
                .orElseThrow(()->new IllegalArgumentException("RoutineService-delete : 루틴을 찾을 수 없습니다"));

        //예외 처리
        if (target.getUser().getId()!=userId){
            throw new IllegalArgumentException("RoutineService-delete : 잘못된 접근 : 로그인한 사용자의 루틴이 아닙니다");
        }
        
        //루틴 삭제
        routineRepository.delete(target);
        
        return RoutineDTO.toDTO(target);
    }
}

