package com.econetwireless.epay.business.integrations.api;

import com.econetwireless.utils.pojo.INBalanceResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;

/**
 * Created by tnyamakura on 17/3/2017.
 */
public interface ChargingPlatform {

    INBalanceResponse enquireBalance(final String partnerCode, final String msisdn);
    INCreditResponse creditSubscriberAccount(final INCreditRequest inCreditRequest);
}
