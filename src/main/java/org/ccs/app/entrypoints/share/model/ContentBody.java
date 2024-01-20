package org.ccs.app.entrypoints.share.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;

public record ContentBody<T>(int code, String message, String traceId, T contents) {

    public static <T> String serialize(ContentBody<T> contentBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Include non-null values only in the serialized output
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return objectMapper.writeValueAsString(contentBody);
    }
}
