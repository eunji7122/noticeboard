package com.project.noticeboard.domain.member;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String username;

    @NotNull
    @Column(name = "phone_number")
    private int phoneNumber;

    public Member() {
    }

    public Member(Long id, String email, String password, String username, int phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }
}
