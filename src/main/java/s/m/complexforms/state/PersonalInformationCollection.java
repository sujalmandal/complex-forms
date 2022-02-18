package s.m.complexforms.state;

import lombok.extern.slf4j.Slf4j;
import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.dto.PersonalInformationRequest;
import s.m.complexforms.dto.PersonalInformationResponse;
import s.m.complexforms.statemachine.ActionEnum;
import s.m.complexforms.statemachine.StateEnum;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public Output execute(Input request) {
        log.info("received request : {}", request);
        // actually process the request
        PersonalInformationResponse result = process(request);
        Output output = toOutputFromStateResponse(result);
        // setup options for the next actions from this state */
        output.setActionOptions(getActionOptions());
        return output;
    }

    private PersonalInformationResponse process(Input request){
        return new PersonalInformationResponse();
    }

    @Override
    public Output toOutputFromStateResponse(PersonalInformationResponse response) {
        return new Output();
    }

    @Override
    public PersonalInformationRequest toStateRequestFromInput(Input input) {
        PersonalInformationRequest request = new PersonalInformationRequest();
        return request;
    }
}
