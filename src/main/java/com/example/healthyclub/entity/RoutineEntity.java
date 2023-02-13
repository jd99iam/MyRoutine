package com.example.healthyclub.entity;

import com.example.healthyclub.dto.RoutineDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name="routine")
public class RoutineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //user_id FK
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    //날짜를 사용하기 위함
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    //루틴
    @Column
    private String routine;

    //루틴 사진
    @Column
    @Nullable
    private String image;

    //유저 엔티티와 루틴 dto로 루틴 엔티티 생성
    public static RoutineEntity toEntity(RoutineDTO routineDTO, UserEntity userEntity){
        if (routineDTO.getId()!=null){
            throw new IllegalArgumentException("루틴 생성 실패. 루틴 아이디가 없어야 함");
        }

        return new RoutineEntity(
                routineDTO.getId(),
                userEntity,
                routineDTO.getDate(),
                routineDTO.getRoutine(),
                null
        );
    }

    //루틴 엔티티를 수정 폼으로 받은 dto를 이용해서 수정하는 메소드
    public void patch(RoutineDTO routineDTO) {
        this.date=routineDTO.getDate();
        this.routine=routineDTO.getRoutine();
    }
}
