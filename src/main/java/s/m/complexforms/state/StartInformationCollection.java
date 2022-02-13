package s.m.complexforms.state;

import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.form.BinaryFormElement;
import s.m.complexforms.form.Form;
import s.m.complexforms.form.TextFormElement;
import s.m.complexforms.statemachine.ActionEnum;
import s.m.complexforms.statemachine.State;
import s.m.complexforms.statemachine.StateEnum;

import java.util.Collections;
import java.util.List;

public class StartInformationCollection implements State<Input, Output> {

    @Override
    public StateEnum getCurrentState() {
        return StateEnum.START;
    }

    @Override
    public List<ActionEnum> getActionOptions(Input input) {
        return Collections.singletonList(ActionEnum.TO_NEXT);
    }

    @Override
    public Output execute(Input input) {
        Output output = new Output();
        output.setCurrentStep(this.getCurrentState());
        output.setActionOptions(this.getActionOptions(input));
        Form personalInfoCollectionForm = Form
                .builder("Personal information section")
                .withElement(new TextFormElement("Your Name","name"))
                .withElement(new TextFormElement("Your Gender","gender"))
                .withElement(new BinaryFormElement("Currently a student?","is_student"))
                .build();
        /* render this form in the UI */
        output.setFormToFill(personalInfoCollectionForm);
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
