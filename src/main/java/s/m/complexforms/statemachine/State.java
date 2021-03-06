package s.m.complexforms.statemachine;
import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;


/*
 Represents a unit of work that is executed when a particular state is reached
 The implementations of this interface must take care of the following responsibilities
  1) Execute the unit of work associated with this state.
  2) Depending on the execution and conditions internal to this state, provide a list of
     possible actions that can be performed next to go to a new state.
*/

public interface State<StateRequest,StateResponse> {
    StateEnum getCurrentState();
    Output<StateResponse> execute(Input<StateRequest> input);
}
