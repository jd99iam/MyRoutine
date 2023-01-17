package com.example.healthyclub.dto;


import com.example.healthyclub.entity.RoutineEntity;
import com.example.healthyclub.repository.UserRepository;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RoutineDTO {


    private Long id;

    private Long userid;

    private LocalDate date;

    private String routine;

    //엔티티를 DTO로 변환하는 메소드
    public static RoutineDTO toDTO(RoutineEntity routineEntity) {
        return new RoutineDTO(
                routineEntity.getId(),
                routineEntity.getUser().getId(),
                routineEntity.getDate(),
                routineEntity.getRoutine()
        );
    }
}
