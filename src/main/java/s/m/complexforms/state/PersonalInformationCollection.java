package s.m.complexforms.state;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import s.m.complexforms.common.FormStateConstants;
import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.dto.PersonalInformationRequest;
import s.m.complexforms.entity.Person;
import s.m.complexforms.form.Form;
import s.m.complexforms.form.NumberFormElement;
import s.m.complexforms.form.TextFormElement;
import s.m.complexforms.statemachine.AbstractState;
import s.m.complexforms.statemachine.InputOutputUtil;
import s.m.complexforms.statemachine.StateEnum;

import java.util.UUID;

import static s.m.complexforms.statemachine.ActionEnum.*;
import static s.m.complexforms.statemachine.StateEnum.PERSONAL_INFORMATION_FORM;

@Slf4j
public class PersonalInformationCollection extends AbstractState<PersonalInformationRequest, Person> {

    @Override
    public StateEnum getCurrentState() {
        return PERSONAL_INFORMATION_FORM;
    }

    private Input<PersonalInformationRequest> input;
    private Person executionResult;
    private PersonalInformationRequest personalInformationRequest;

    @Override
    public Output<Person> execute(Input<PersonalInformationRequest> input) {
        this.input = input;
        log.info("received request : {}", input);
        // actually process the request
        this.personalInformationRequest = getStateRequest(input);
        this.executionResult = process();
        return getOutput();
    }

    private Person process(){
        log.info("Processing {}",personalInformationRequest);
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(personalInformationRequest,person);
        return person;
    }

    @Override
    public Output<Person> getOutput() {
        Output<Person> output = new Output<>();
        /* decide the next state */
        if(personalInformationRequest.getIsStudent()) {
            output.setNextAction(TO_EDUCATION_INFO);
            /* set up education entry form */
            Form educationInformationForm = Form
                    .builder("Education information section")
                    .withElement(new TextFormElement("College name", FormStateConstants.COLLEGE_NAME))
                    .withElement(new TextFormElement("Expected graduation date",FormStateConstants.GRADUATION_YEAR))
                    .withElement(new TextFormElement("Your Major in College",FormStateConstants.MAJOR))
                    .withElement(new NumberFormElement("current CGPA",FormStateConstants.CGPA))
                    .build();
            output.setFormToFill(educationInformationForm);
        }
        else {
            output.setNextAction(TO_WORK_INFO);
            /* set up work info */
            Form workInformationForm = Form
                    .builder("Work information section")
                    .withElement(new NumberFormElement("Total experience", FormStateConstants.TOTAL_EXPERIENCE))
                    .withElement(new NumberFormElement("Your current annual compensation",FormStateConstants.CURRENT_SALARY))
                    .withElement(new TextFormElement("Industry you work in",FormStateConstants.INDUSTRY))
                    .withElement(new TextFormElement("Current employer name",FormStateConstants.CURRENT_EMPLOYER))
                    .withElement(new TextFormElement("Current position",FormStateConstants.CURRENT_POSITION))
                    .build();
            output.setFormToFill(workInformationForm);
        }

        output.setBackAction(TO_START);
        output.setResponse(executionResult);
        return output;
    }

    @Override
    protected PersonalInformationRequest getStateRequest(Input input) {
        return InputOutputUtil.get(input.getRequest(), new TypeReference<>(){});
    }


}
