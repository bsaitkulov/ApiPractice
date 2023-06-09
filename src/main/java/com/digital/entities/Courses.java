package com.digital.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Courses {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String id;
    String name;
    int code;
    String description;
    int category_id;

    @JsonProperty("user_id")
    String user_id;
    @JsonProperty("course_id")
    String course_id;
    @JsonProperty("user")
    String [] users;

}
