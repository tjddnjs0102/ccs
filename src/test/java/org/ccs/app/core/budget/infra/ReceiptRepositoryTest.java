package org.ccs.app.core.budget.infra;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.ccs.app.config.QueryDslConfig;
import org.ccs.app.core.budget.domain.Bank;
import org.ccs.app.core.budget.domain.BankCode;
import org.ccs.app.core.budget.domain.Receipt;
import org.ccs.app.core.budget.domain.ReceiptItem;
import org.ccs.app.core.budget.model.QueryReceiptResult;
import org.ccs.app.core.share.AbstractRepositoryTest;
import org.ccs.app.core.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(QueryDslConfig.class)
class ReceiptRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    JPAQueryFactory queryDsl;

    @Autowired
    ReceiptRepository receiptRepository;

    @Test
    void test_search() {
        // given
        User user = User.builder()
                .name("테스트")
                .build();

        em.persist(user);

        Receipt receipt = Receipt.builder()
                .requester(user.getId())
                .depositAccount(new Bank(BankCode.KB, "110-110-4444"))
                .build();

        em.persist(receipt);

        ReceiptItem item1 = ReceiptItem.builder()
                .goodsName("item 1")
                .amount(BigDecimal.valueOf(9900))
                .quantity(10)
                .build();

        ReceiptItem item2 = ReceiptItem.builder()
                .goodsName("item 2")
                .amount(BigDecimal.valueOf(19900))
                .quantity(1)
                .build();

        receipt.addItem(item1);
        receipt.addItem(item2);

        em.persist(item1);
        em.persist(item2);

        em.flush();
        em.clear();

        // when
        List<QueryReceiptResult> results = receiptRepository.search();

        // then
        assertAll(
                () -> assertEquals(1, results.size()),
                () -> assertEquals(2, results.get(0).getItems().size())
        );
    }
}