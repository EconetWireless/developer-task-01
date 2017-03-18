package com.econetwireless.epay.api.processors.api;

import com.econetwireless.epay.api.rest.messages.TransactionsResponse;
import com.econetwireless.utils.messages.AirtimeBalanceResponse;
import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.messages.AirtimeTopupResponse;

/**
 * Created by tnyamakura on 18/3/2017.
 */
public interface EpayRequestProcessor {

    AirtimeBalanceResponse enquireAirtimeBalance(String partnerCode, String msisdn);
    AirtimeTopupResponse creditAirtime(AirtimeTopupRequest airtimeTopupRequest);

}
