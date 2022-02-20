package s.m.complexforms.state;

import lombok.extern.slf4j.Slf4j;
import s.m.complexforms.common.FormStateConstants;
import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.form.BinaryFormElement;
import s.m.complexforms.form.Form;
import s.m.complexforms.form.TextFormElement;
import s.m.complexforms.statemachine.AbstractState;
import s.m.complexforms.statemachine.ActionEnum;
import s.m.complexforms.statemachine.StateEnum;

import java.util.Collections;
import java.util.List;

import static s.m.complexforms.statemachine.ActionEnum.TO_PERSONAL_INFO;

/* very first state */
@Slf4j
public class StartInformationCollection extends AbstractState<Input, Output> {

    @Override
    public StateEnum getCurrentState() {
        return StateEnum.START;
    }

    private Input input;

    @Override
    public Output execute(Input input) {
        this.input = input;
        Output output = getOutput();
        log.info("executing StartInformationCollection state");
        return output;
    }

    @Override
    public Output getOutput() {
        Output out = new Output();
        out.setNextAction(TO_PERSONAL_INFO);
        /* generate the dynamic form to display for collecting personal information */
        Form personalInfoCollectionForm = Form
                .builder("Personal information section")
                .withElement(new TextFormElement("Your Name", FormStateConstants.NAME))
                .withElement(new TextFormElement("Your Gender",FormStateConstants.GENDER))
                .withElement(new BinaryFormElement("Currently a student?",FormStateConstants.IS_STUDENT))
                .build();
        /* render this form in the UI */
        out.setFormToFill(personalInfoCollectionForm);
        out.setBackAction(null);
        return out;
    }

    @Override
    public Input getStateRequest() {
        return input;
    }
}
