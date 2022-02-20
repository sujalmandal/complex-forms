package s.m.complexforms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import s.m.complexforms.statemachine.ActionEnum;
import s.m.complexforms.statemachine.StateEnum;

import java.util.Stack;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Input<StateRequest> {
    /* optional request */
    StateRequest request;
    StateEnum fromState;
    ActionEnum action;
    Stack<StateEnum> sequence;
}
