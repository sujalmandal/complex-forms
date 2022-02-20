package s.m.complexforms.state;

import lombok.extern.slf4j.Slf4j;
import s.m.complexforms.common.FormStateConstants;
import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.dto.PersonalInformationRequest;
import s.m.complexforms.dto.PersonalInformationResponse;
import s.m.complexforms.form.BinaryFormElement;
import s.m.complexforms.form.TextFormElement;
import s.m.complexforms.statemachine.AbstractState;
import s.m.complexforms.statemachine.ActionEnum;
import s.m.complexforms.statemachine.StateEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static s.m.complexforms.statemachine.ActionEnum.*;
import static s.m.complexforms.statemachine.StateEnum.PERSONAL_INFORMATION_FORM;

@Slf4j
public class PersonalInformationCollection extends AbstractState<PersonalInformationRequest, PersonalInformationResponse> {

    @Override
    public StateEnum getCurrentState() {
        return PERSONAL_INFORMATION_FORM;
    }

    @Override
    public List<ActionEnum> getActionOptions() {
        return Arrays.asList(
                TO_EDUCATION_INFO,
                TO_WORK_INFO,
                TO_BACK
        );
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
        /* setup options for the next actions from this state
         *
         * Set up the form to show as a result for this state that can act as an
         * input for the next possible states
         */
        /* if the person is a student, do not allow the work info state to be accessible */
        if(personalInformationRequest.getIsStudent()){
            output.setActionOptions(getActionOptions()
                    .stream().filter(actionEnum -> actionEnum!= TO_WORK_INFO)
                    .collect(Collectors.toList()));

        }
        else{
            output.setActionOptions(getActionOptions());
        }
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
