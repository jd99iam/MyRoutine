package com.example.healthyclub.controller;

import com.example.healthyclub.dto.RoutineDTO;
import com.example.healthyclub.entity.RoutineEntity;
import com.example.healthyclub.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;


    //루틴 생성
    @PostMapping("/routine/{userId}")
    public ResponseEntity<RoutineDTO> create(@RequestBody RoutineDTO routineDTO, @PathVariable Long userId){

        //서비스를 이용해 루틴 엔티티 생성 (dto 전달 -> 엔티티 생성 , DB 저장 -> dto 반환)
        RoutineDTO created = routineService.create(routineDTO, userId);

        //응답코드와 함께 반환
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    //루틴 읽어오기 (유저의 모든 루틴을 읽어옴)
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
    public ResponseEntity<RoutineDTO> update(@RequestBody RoutineDTO routineDTO, @PathVariable Long userId, @PathVariable Long routineId){

        //서비스에게 위임해서 수정
        RoutineDTO updated = routineService.update(routineDTO,userId,routineId);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //루틴 삭제
    @DeleteMapping("/routine/{userId}/{routineId}")
    public ResponseEntity<RoutineDTO> delete(@PathVariable Long userId, @PathVariable Long routineId){

        //서비스에게 위임해서 삭제
        RoutineDTO deleted = routineService.delete(userId,routineId);

        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }



}
