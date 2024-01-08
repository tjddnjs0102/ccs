package org.ccs.app.core.budget.model;

import org.ccs.app.core.budget.domain.ReceiptItem;

import java.math.BigDecimal;

public class QueryReceiptItemResult {

    private Long receiptItemId;
    private String goodsName;
    private BigDecimal amount;
    private Integer quantity;
    private String attachedGoodsImage;
    private String goodsWebLink;

    public QueryReceiptItemResult(ReceiptItem item) {
        this.receiptItemId = item.getId();
        this.goodsName = item.getGoodsName();
        this.amount = item.getAmount();
        this.quantity = item.getQuantity();
        this.attachedGoodsImage = item.getAttachedGoodsImage();
        this.goodsWebLink = item.getGoodsWebLink();
    }
}
