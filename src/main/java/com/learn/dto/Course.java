package com.learn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Course {
    private int id;
    private String title;
    private int price;
    private String photoPath;
    private String presentationPath;
    private String description;
    private User[] user;
}

