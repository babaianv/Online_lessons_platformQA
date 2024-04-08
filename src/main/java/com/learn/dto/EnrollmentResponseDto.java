package com.learn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class EnrollmentResponseDto {

    private int id;
    private String enrollmentDate;
    private String status;
    private Course course;
}

