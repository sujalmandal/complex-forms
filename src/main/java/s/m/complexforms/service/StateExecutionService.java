package s.m.complexforms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.statemachine.State;
import s.m.complexforms.statemachine.StateFactory;

import java.util.Stack;

@Service
@Slf4j
public class StateExecutionService {

    public Output executeState(Input input) {
        //fetch the current state
        State thisState = StateFactory.getState(input.getFromState(), input.getAction());
        log.info("Current state : {}",thisState.getCurrentState());
        //execute the operation in this current state
        Output output = thisState.toOutputFromStateResponse(
                thisState.execute(thisState.toStateRequestFromInput(input)));
        //set the executed state as the current state
        output.setCurrentStep(thisState.getCurrentState());
        // capture the sequence of states
        output.setSequence(input.getSequence()==null?new Stack<>():input.getSequence());
        output.getSequence().push(thisState.getCurrentState());
        return output;
    }

}
