package com.project.noticeboard.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberLoginDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
