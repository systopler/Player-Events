package ru.moa.player.events.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.annotation.PostConstruct;

import static com.fasterxml.jackson.databind.DeserializationFeature.READ_ENUMS_USING_TO_STRING;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_ENUMS_USING_TO_STRING;

public class DataObjectMapper extends ObjectMapper {
    @PostConstruct
    public void init() {
        registerModule(new JavaTimeModule());
        setDateFormat(new StdDateFormat());
        configure(SerializationFeature.INDENT_OUTPUT, true);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        enable(WRITE_ENUMS_USING_TO_STRING);
        enable(READ_ENUMS_USING_TO_STRING);
        setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
    }
}
