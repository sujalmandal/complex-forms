package s.m.complexforms.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property="formType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NumberFormElement.class, name = "NumberFormElement"),
        @JsonSubTypes.Type(value = TextFormElement.class , name = "TextFormElement"),
        @JsonSubTypes.Type(value = BinaryFormElement.class , name = "BinaryFormElement")}
)
public interface FormElement<T> {

    FormElementType getType();
    String getLabel();
    String getName();
    T getValue();

    default String getId(){
        return UUID.randomUUID().toString();
    }
}
