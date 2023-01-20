package com.example.healthyclub.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "friends")
public class FriendEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String friend;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
