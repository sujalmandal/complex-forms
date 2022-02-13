package s.m.complexforms.dto;

import lombok.Data;
import s.m.complexforms.form.Form;
import s.m.complexforms.statemachine.ActionEnum;
import s.m.complexforms.statemachine.StateEnum;

import java.util.Stack;

@Data
public class Input {
    Form filledForm;
    StateEnum fromState;
    ActionEnum action;
    Stack<StateEnum> sequence;
}
