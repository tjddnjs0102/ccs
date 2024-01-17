package org.ccs.app.entrypoints.budget.service;

import org.ccs.app.core.budget.model.QueryReceiptResult;

import java.util.List;

public interface ReceiptService {

    List<QueryReceiptResult> getMyReceipts();
}