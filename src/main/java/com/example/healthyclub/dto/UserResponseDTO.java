package com.example.healthyclub.dto;


import com.example.healthyclub.entity.UserEntity;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String email;
    private String nickname;
    private String userId;
    private String token;

    public UserResponseDTO(UserEntity user){
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.userId = user.getUserId();
    }
}