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
    private Long id;

    public UserResponseDTO(UserEntity user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.userId = user.getUserId();
    }
}