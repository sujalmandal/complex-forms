package s.m.complexforms.state;

import lombok.extern.slf4j.Slf4j;
import s.m.complexforms.common.FormStateConstants;
import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.dto.PersonalInformationRequest;
import s.m.complexforms.dto.PersonalInformationResponse;
import s.m.complexforms.form.BinaryFormElement;
import s.m.complexforms.form.Form;
import s.m.complexforms.form.NumberFormElement;
import s.m.complexforms.form.TextFormElement;
import s.m.complexforms.statemachine.AbstractState;
import s.m.complexforms.statemachine.StateEnum;


import static s.m.complexforms.statemachine.ActionEnum.*;
import static s.m.complexforms.statemachine.StateEnum.PERSONAL_INFORMATION_FORM;

@Slf4j
public class PersonalInformationCollection extends AbstractState<PersonalInformationRequest, PersonalInformationResponse> {

    @Override
    public StateEnum getCurrentState() {
        return PERSONAL_INFORMATION_FORM;
    }

    private Input input;
    private PersonalInformationResponse executionResult;
    private PersonalInformationRequest personalInformationRequest;

    @Override
    public Output execute(Input input) {
        this.input = input;
        log.info("received request : {}", input);
        // actually process the request
        this.personalInformationRequest = getStateRequest();
        this.executionResult = process();
        return getOutput();
    }

    private PersonalInformationResponse process(){
        log.info("Processing {}",personalInformationRequest);
        return new PersonalInformationResponse();
    }

    @Override
    public Output getOutput() {
        Output output = new Output();
        /* decide the next state */
        if(personalInformationRequest.getIsStudent()) {
            output.setNextAction(TO_EDUCATION_INFO);
            /* set up education entry form */
            Form educationInformationForm = Form
                    .builder("Education information section")
                    .withElement(new TextFormElement("College name", FormStateConstants.COLLEGE_NAME))
                    .withElement(new TextFormElement("Expected graduation date",FormStateConstants.GRADUATION_YEAR))
                    .withElement(new TextFormElement("Your Major in College",FormStateConstants.MAJOR))
                    .withElement(new NumberFormElement("current CGPA",FormStateConstants.CGPA))
                    .build();
            output.setFormToFill(educationInformationForm);
        }
        else {
            output.setNextAction(TO_WORK_INFO);
            /* set up work info */
            Form workInformationForm = Form
                    .builder("Work information section")
                    .withElement(new NumberFormElement("Total experience", FormStateConstants.TOTAL_EXPERIENCE))
                    .withElement(new NumberFormElement("Your current annual compensation",FormStateConstants.CURRENT_SALARY))
                    .withElement(new TextFormElement("Industry you work in",FormStateConstants.INDUSTRY))
                    .withElement(new TextFormElement("Current employer name",FormStateConstants.CURRENT_EMPLOYER))
                    .withElement(new TextFormElement("Current position",FormStateConstants.CURRENT_POSITION))
                    .build();
            output.setFormToFill(workInformationForm);
        }

        output.setBackAction(TO_START);
        return output;
    }

    @Override
    public PersonalInformationRequest getStateRequest() {
        log.info("Converting '{}' form to PersonalInformationRequest", input.getFilledForm().getName());
        PersonalInformationRequest request = new PersonalInformationRequest();
        input.getFilledForm().getElements().forEach(formElement -> {
            switch (formElement.getName()){
                case FormStateConstants.NAME:
                    if(formElement instanceof TextFormElement){
                        request.setName(((TextFormElement) formElement).getValue());
                    }
                    break;
                case FormStateConstants.GENDER:
                    if(formElement instanceof TextFormElement){
                        request.setGender(((TextFormElement) formElement).getValue());
                    }
                    break;
                case FormStateConstants.IS_STUDENT:
                    if(formElement instanceof BinaryFormElement){
                        request.setIsStudent(((BinaryFormElement) formElement).getValue());
                    }
                    break;
                default:
                    throw new IllegalArgumentException(
                            String.format("Unknown {} element", formElement.getName())
                    );
            }
        });
        return request;
    }
}
