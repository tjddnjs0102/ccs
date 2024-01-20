package org.ccs.app.entrypoints.share.model;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class SliceBody<T> {

    private final ContentBody<T> contentBody;
    private final Boolean next;

    public SliceBody(int code, String message, String traceId, T contents, Boolean next) {
        this.contentBody = new ContentBody<>(code, message, traceId, contents);
        this.next = next;
    }

    public SliceBody(int code, String message, String traceId, T contents) {
        this(code, message, traceId, contents, null);
    }
}
