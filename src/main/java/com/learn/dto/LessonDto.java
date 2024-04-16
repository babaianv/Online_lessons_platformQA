package com.learn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class LessonDto {

    private int id;
    private String title;
    private String photoPath;
    private String content;
}

