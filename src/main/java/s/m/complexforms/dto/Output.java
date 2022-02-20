package s.m.complexforms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import s.m.complexforms.form.Form;
import s.m.complexforms.statemachine.ActionEnum;
import s.m.complexforms.statemachine.StateEnum;

import java.util.Stack;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Output<StateResponse> {
    Form formToFill;
    /* optional response */
    StateResponse response;
    /* a combination of current step & a particular action defines the next state of the system */
    StateEnum currentStep;
    ActionEnum nextAction;
    ActionEnum backAction;
    Stack<StateEnum> sequence;
}
