package org.ccs.app.core.budget.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ccs.app.core.share.domain.BaseCreatedAndUpdatedDateTime;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "css_receipt")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
@NoArgsConstructor
public class Receipt extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "receipt")
    private List<ReceiptItem> items;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    private String requester;

    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "back_code")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "back_account_number")),
    })
    @Embedded
    private Bank depositAccount;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    private ReceiptStatus status;

    @Column(name = "attached_file")
    private String attachedFile;

    // TODO : 처리자(수정자) 정보 추가 - 로그인 정보 추가 시
    // TODO : 요청자(requester) 타입을 Long으로 변경 - 로그인 정보 추가 시
}
