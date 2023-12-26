package org.ccs.app.entrypoints.share.model;

import lombok.Getter;
import lombok.ToString;

//TODO: record 로 전환하기
@Getter @ToString
public class ContentBody<T> {
    private final int code;
    private final String message;
    private final String traceId;
    private final T contents;

    public ContentBody(int code, String message, String traceId, T contents) {
        this.code = code;
        this.message = message;
        this.traceId = traceId;
        this.contents = contents;
    }
}
