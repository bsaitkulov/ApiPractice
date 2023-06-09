package com.digital.utils;

import com.digital.entities.Courses;
import com.digital.entities.User;

public class EntitiesManager {

    public static User generateUser(){
        return User.builder()
                .firstName(FakeDataProvider.generateFirstName())
                .lastName(FakeDataProvider.generateLastName())
                .email(FakeDataProvider.generateEmail())
                .password(FakeDataProvider.generatePassword())
                .login(FakeDataProvider.generateLogin())
                .build();
    }
    public static Courses generateCourses(){
        return Courses.builder()
                .name(FakeDataProvider.generateCourseName())
                .code(FakeDataProvider.generateCode())
                .description(FakeDataProvider.generateDescription())
//                .category_id(FakeDataProvider.generateCategoryId())
                .build();
    }
}
