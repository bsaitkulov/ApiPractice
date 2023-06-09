package com.digital.api.asserts;

import com.digital.entities.Courses;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
public class CoursesAssert {

    Courses actualCourses;

    public CoursesAssert(Courses actualCourses) {
        this.actualCourses = actualCourses;
    }
    public CoursesAssert isCorrectName(String expectedName) {
        Assert.assertEquals(actualCourses.getName(), expectedName);
        log.info("Name is correct, Expected {}, Actual {}", expectedName, actualCourses.getName());
        return this;
    }
    public CoursesAssert isCorrectCode(int expectedCode) {
        Assert.assertEquals(actualCourses.getCode(), expectedCode);
        log.info("Code is correct, Expected {}, Actual {}", expectedCode, actualCourses.getCode());
        return this;
    }
    public CoursesAssert isUserAddedToCourse(String id) {
        Assert.assertEquals(id, actualCourses.getUsers()[0]);
        return this;
    }
}
