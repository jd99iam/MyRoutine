package com.example.healthyclub.dto;


import com.example.healthyclub.entity.RoutineEntity;
import com.example.healthyclub.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Slf4j
public class RoutineResponseDTO {

    private static String uploadRootPath="C:/routine_images";

    private Long id;

    private Long userid;

    private LocalDate date;

    private String routine;

    private byte[] image;



    //엔티티를 DTO로 변환하는 메소드
    public static RoutineResponseDTO toDTO(RoutineEntity routineEntity) {

        String image = routineEntity.getImage();
        log.info("DB상의 이미지 경로 : "+image);

        if (image!=null){
            String fullPath = uploadRootPath + File.separator + image;
            log.info("fullpath : "+fullPath);

            //해당 경로를 파일 객체로 포장
            File targetFile = new File(fullPath);

            byte[] rawImageData = null;

            //혹시 해당 파일이 존재하지 않으면 예외가 발생(FileNotFoundException)
            if(!targetFile.exists()) {
                rawImageData = null;
                log.info("해당 파일이 존재하지 않음");
            }

            try {
                //파일 데이터를 바이트배열로 포장 (blob 데이터)
                rawImageData = FileCopyUtils.copyToByteArray(targetFile);
            } catch(IOException e){
                log.info("바이트배열로 바꾸는 도중 에러");
            }

            return new RoutineResponseDTO(
                    routineEntity.getId(),
                    routineEntity.getUser().getId(),
                    routineEntity.getDate(),
                    routineEntity.getRoutine(),
                    rawImageData
                    );
        }

        log.info("이미지가 null임");

        return new RoutineResponseDTO(
                routineEntity.getId(),
                routineEntity.getUser().getId(),
                routineEntity.getDate(),
                routineEntity.getRoutine(),
                null
                );
    }
}
