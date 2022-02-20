package s.m.complexforms.statemachine;

import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;

public abstract class AbstractState<StateRequest,StateResponse>
        implements State<StateRequest,StateResponse> {
    protected abstract Output<StateResponse> getOutput();
    protected abstract StateRequest getStateRequest(Input input);
}
