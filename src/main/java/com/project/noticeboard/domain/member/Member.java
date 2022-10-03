package com.project.noticeboard.domain.member;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String provider;   // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지
    private String providerId; // oauth2를 이용할 경우 아이디값

    public Member() {
    }

    @Builder
    public Member(String email, String password, String username, String phoneNumber, Role role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String email, String password, String username, String phoneNumber, Role role, String provider, String providerId) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}
