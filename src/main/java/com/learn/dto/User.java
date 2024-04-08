package com.learn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private Cart[] cart;
    private Token[] token;
    private GrantedAuthority[] authorities;
}

