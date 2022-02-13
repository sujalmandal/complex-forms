package s.m.complexforms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import s.m.complexforms.form.Form;
import s.m.complexforms.statemachine.ActionEnum;
import s.m.complexforms.statemachine.StateEnum;

import java.util.List;
import java.util.Stack;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Output {
    Form formToFill;
    /* a combination of current step & a particular action defines the next state of the system */
    StateEnum currentStep;
    List<ActionEnum> actionOptions;
    Stack<StateEnum> sequence;
}
