package s.m.complexforms.form;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberFormElement implements FormElement<Integer>{

    private Integer value;
    private String label;
    private String name;

    public NumberFormElement(String label, String name) {
        this.label = label;
        this.name = name;
    }

    @Override
    public FormElementType getType() {
        return FormElementType.TEXT;
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
    public Integer getValue() {
        return value;
    }
}
