package s.m.complexforms.form;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BinaryFormElement implements FormElement<Boolean>{

    private Boolean value;
    private String label;
    private String name;

    public BinaryFormElement(String label, String name) {
        this.label = label;
        this.name = name;
    }

    @Override
    public FormElementType getType() {
        return FormElementType.BOOLEAN;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
