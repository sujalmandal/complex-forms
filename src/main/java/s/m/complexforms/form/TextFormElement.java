package s.m.complexforms.form;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TextFormElement implements FormElement<String>{

    private String value;
    private String label;
    private String name;

    public TextFormElement(String label, String name) {
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
    public String getValue() {
        return value;
    }
}
