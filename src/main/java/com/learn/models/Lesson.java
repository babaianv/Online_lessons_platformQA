package com.learn.models;

public class Lesson {

    private String number;
    private  String title;
    private  String content;
    private  String lessonPhoto;

    public Lesson setTitle(String title) {
        this.title = title;
        return this;
    }

    public Lesson setContent(String content) {
        this.content = content;
        return this;
    }

    public Lesson setLessonPhoto(String lessonPhoto) {
        this.lessonPhoto = lessonPhoto;
        return this;
    }

    public Lesson setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLessonPhoto() {
        return lessonPhoto;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "number='" + number + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

