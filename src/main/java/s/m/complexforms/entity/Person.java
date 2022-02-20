package s.m.complexforms.entity;

import lombok.Data;

@Data
public class Person {

    private String id;
    private String name;
    private String gender;
    private Boolean isStudent;

    private String collegeName;
    private String graduationYear;
    private String major;
    private Integer CGPA;

    private Integer totalExperience;
    private Integer currentSalary;
    private String currentIndustry;
    private String currentEmployer;
    private String currentPosition;

}
