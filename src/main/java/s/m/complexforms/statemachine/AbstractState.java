package s.m.complexforms.statemachine;

import s.m.complexforms.dto.Output;

public abstract class AbstractState<StateRequest,StateResponse> implements State<StateRequest,StateResponse> {
    protected abstract Output getOutput();
    protected abstract StateRequest getStateRequest();
}
