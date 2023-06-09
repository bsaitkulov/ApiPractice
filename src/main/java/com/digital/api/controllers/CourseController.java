package com.digital.api.controllers;

import com.digital.api.ApiRequest;
import com.digital.entities.Courses;
import com.digital.utils.JsonUtils;
import io.restassured.response.Response;
import java.util.HashMap;
import static com.digital.api.TalentLMSBaseEndpoints.*;


public class CourseController extends ApiRequest {

    public CourseController(String baseUrl, String apiKey) {
        super(baseUrl, apiKey);
    }

    public Courses[] getCourses(){
        return super.get(generateEndpoints(API, V1, GET_COURSE)).as(Courses[].class);
    }

    public Courses createCourse(Courses courses){
        String coursesJson = JsonUtils.convertJavaObjectToJson(courses);
        return super.post(generateEndpoints(API,V1,CREATE_COURSE),coursesJson).as(Courses.class);
    }

    public Response courseDelete(String id) {
        return super.post(generateEndpoints(API,V1,DELETE_COURSE), "course_id="+id);
    }

    public Courses getSingleCourse(String by, String id) {
        HashMap<String, String> map = new HashMap<>() {{
            put(by,id);
        }};
        return get(generateEndpoints(API, V1, GET_COURSE, formatParameters(map))).as(Courses.class);
    }

    public Response addUserToCourse1(String body){
        return super.post(generateEndpoints(API,V1,ADD_USER_TO_COURSE), body);
    }
    public Response addUserToCourse(String userId, String courseId){
        Courses courses = Courses.builder().user_id(userId).course_id(courseId).build();
        String addUserToCourses = JsonUtils.convertJavaObjectToJson(courses);
        return super.post(generateEndpoints(API,V1,ADD_USER_TO_COURSE), addUserToCourses);
    }

    public static void main(String[] args) {
        CourseController courseController = new CourseController("https://bravos.talentlms.com", "pPLKsrpvy4hz7rQAe5V5v3wCg3jT4T");
        courseController.addUserToCourse("20", "124");
    }

}
