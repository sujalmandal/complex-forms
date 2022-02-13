package s.m.complexforms.form;

import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
public class Form {

    private final String name;
    private final Set<FormElement> elements;

    private Form(String name, Set<FormElement> elements) {
        this.name = name;
        this.elements = elements;
    }

    public static Builder builder(String name){
        return new Builder(name);
    }

    public static class Builder{

        private Form formObject;

        private Builder(String name){
            formObject = new Form(name, new LinkedHashSet<>());
        }

        public Builder withElement(FormElement element){
            this.formObject.getElements().add(element);
            return this;
        }

        public Form build(){
            return formObject;
        }
    }
}
