package s.m.complexforms.statemachine;

import s.m.complexforms.state.PersonalInformationCollection;
import s.m.complexforms.state.StartInformationCollection;

import java.util.Objects;

public class StateFactory {

    private StateFactory(){};

    public static State getState(StateEnum fromState, ActionEnum action) {
        if(Objects.isNull(fromState)){
            return new StartInformationCollection();
        }
        else if(fromState == StateEnum.START && action == ActionEnum.TO_PERSONAL_INFO){
            return new PersonalInformationCollection();
        }
        else if(fromState == StateEnum.PERSONAL_INFORMATION_FORM && action == ActionEnum.TO_START){
            return new StartInformationCollection();
        }
        else if(fromState == StateEnum.PERSONAL_INFORMATION_FORM && action == ActionEnum.TO_WORK_INFO){
            return null;
        }
        else if(fromState == StateEnum.PERSONAL_INFORMATION_FORM && action == ActionEnum.TO_EDUCATION_INFO){
            return null;
        }
        throw new IllegalArgumentException("Bad from state/action passed");
    }
}
