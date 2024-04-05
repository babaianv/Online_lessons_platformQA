package com.learn.dto;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Role {
    private int id;
    private String authority;
}

