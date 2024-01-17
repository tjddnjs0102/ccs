package org.ccs.app.core.share.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Getter @ToString
public abstract class QueryParameter {
    protected final static int DEFAULT_PAGE_SIZE = 20;
    protected final static Sort DEFULT_SORT = Sort.unsorted();

    protected final Sort sort;
    protected final int pageNumber;
    protected final int pageSize;

    public QueryParameter(Sort sort, int pageNumber, int pageSize) {
        this.sort = Optional.ofNullable(sort).orElse(QueryParameter.DEFULT_SORT);
        this.pageNumber = pageNumber;
        this.pageSize = pageSize == 0 ? QueryParameter.DEFAULT_PAGE_SIZE : pageSize;
    }

    public long getOffset() {
        return (long) pageNumber * pageSize;
    }
}