package com.econetwireless.epay.api.processors.api;

import com.econetwireless.epay.api.rest.messages.TransactionsResponse;

/**
 * Created by tnyamakura on 18/3/2017.
 */
public interface ReportingProcessor {

    TransactionsResponse getPartnerTransactions(String partnerCode);
}
