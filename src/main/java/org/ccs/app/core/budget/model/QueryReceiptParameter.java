package org.ccs.app.core.budget.model;

import lombok.Getter;
import lombok.ToString;
import org.ccs.app.core.share.model.QueryParameter;
import org.springframework.data.domain.Sort;

@Getter @ToString
public class QueryReceiptParameter extends QueryParameter {

    private Long requester;

    public QueryReceiptParameter(Sort sort, int pageNumber, int pageSize, Long requester) {
        super(sort, pageNumber, pageSize);
        this.requester = requester;
    }
}
