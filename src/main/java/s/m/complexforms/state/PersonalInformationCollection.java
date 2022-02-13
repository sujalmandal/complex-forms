package s.m.complexforms.state;

import lombok.extern.slf4j.Slf4j;
import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.statemachine.ActionEnum;
import s.m.complexforms.statemachine.State;
import s.m.complexforms.statemachine.StateEnum;

import java.util.Arrays;
import java.util.List;

import static s.m.complexforms.statemachine.ActionEnum.*;
import static s.m.complexforms.statemachine.StateEnum.PERSONAL_INFORMATION_FORM;

@Slf4j
public class PersonalInformationCollection implements State<Input, Output> {

    @Override
    public StateEnum getCurrentState() {
        return PERSONAL_INFORMATION_FORM;
    }

    @Override
    public List<ActionEnum> getActionOptions(Input input) {
        return Arrays.asList(
                TO_EDUCATION_INFO,
                TO_WORK_INFO,
                TO_BACK
        );
    }

    @Override
    public Output execute(Input input) {
        log.info("received request : ",input);
        Output output = new Output();
        output.setActionOptions(getActionOptions(input));
        return output;
    }

    @Override
    public Output toOutputFromStateResponse(Output output) {
        return output;
    }

    @Override
    public Input toStateRequestFromInput(Input input) {
        return input;
    }
}
