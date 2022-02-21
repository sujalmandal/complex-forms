package s.m.complexforms.state;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import s.m.complexforms.common.FormStateConstants;
import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.form.BinaryFormElement;
import s.m.complexforms.form.Form;
import s.m.complexforms.form.TextFormElement;
import s.m.complexforms.statemachine.AbstractState;
import s.m.complexforms.statemachine.StateEnum;

import static s.m.complexforms.statemachine.ActionEnum.TO_PERSONAL_INFO;

/* very first state */
@Slf4j
public class StartInformationCollection extends AbstractState<Void, Void> {

    @Override
    public StateEnum getCurrentState() {
        return StateEnum.START;
    }

    @Override
    public Output<Void> execute(Input<Void> input) {
        Output<Void> output = getOutput();
        log.info("executing StartInformationCollection state");
        return output;
    }

    @Override
    public Output<Void> getOutput() {
        Output<Void> out = new Output<>();
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
    protected Void getStateRequest(Input input) {
        return null;
    }

}
