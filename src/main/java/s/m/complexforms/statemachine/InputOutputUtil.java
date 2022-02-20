package s.m.complexforms.statemachine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class InputOutputUtil {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    @SneakyThrows
    public static <StateRequest> StateRequest get(Object from, TypeReference<StateRequest> type){
        String json = OBJECT_MAPPER.writeValueAsString(from);
        log.info("Converting {} to {} instance",json, type.getClass());
       return OBJECT_MAPPER.readValue(json,type);
    }

}
