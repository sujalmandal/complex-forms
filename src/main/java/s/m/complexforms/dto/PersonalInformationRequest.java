package s.m.complexforms.dto;

import lombok.Data;

/* this is linked to the response of 'StartInformationCollection' state */
@Data
public class PersonalInformationRequest {
    private String name;
    private String gender;
    private Boolean isStudent;
}
