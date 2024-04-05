package com.learn.dto;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class ChangePasswordDto {

  private String  oldPassword;
  private String  newPassword;
  private String  confirmNewPassword;

}

