package com.learn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
    private String message;
}

