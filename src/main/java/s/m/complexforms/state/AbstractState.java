package s.m.complexforms.state;

import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.statemachine.State;

public abstract class AbstractState<StateRequest,StateResponse> implements State<StateRequest,StateResponse> {
    protected abstract Output toOutputFromStateResponse(StateResponse output);
    protected abstract StateRequest toStateRequestFromInput(Input input);
}
