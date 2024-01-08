package org.ccs.app.core.budget.infra;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.ccs.app.core.budget.model.QueryReceiptResult;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.ccs.app.core.budget.domain.QReceipt.receipt;
import static org.ccs.app.core.budget.domain.QReceiptItem.receiptItem;
import static org.ccs.app.core.user.domain.QUser.user;

@RequiredArgsConstructor
@Repository
public class ReceiptCustomRepositoryImpl implements ReceiptCustomRepository {

    private final JPAQueryFactory queryDsl;

    @Override
    public List<QueryReceiptResult> search() {
        return queryDsl.select(Projections.constructor(QueryReceiptResult.class, receipt, user, receiptItem))
                .from(receipt)
                .join(user).on(receipt.requester.eq(user.id))
                .join(receiptItem).on(receipt.eq(receiptItem.receipt))
                .fetch();
    }
}
