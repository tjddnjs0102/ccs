package org.ccs.app.core.budget.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name = "ccs_receipt_item")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString(exclude = {"receipt"}) // 이런 부분 신경을 안 쓰면 무한루프에 빠집니다.
@NoArgsConstructor
public class ReceiptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Receipt receipt;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "attched_goods_image")
    private String attachedGoodsImage;

    @Column(name = "goods_weblink")
    private String goodsWebLink;
}
