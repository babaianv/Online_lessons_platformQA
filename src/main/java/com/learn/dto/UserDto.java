package com.learn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Builder
public class UserDto {

    private int id;
    private String nickname;
    private String email;
    private String password;
    private List<Role> roles;
}

