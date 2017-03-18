package com.econetwireless.epay.business.services.api;

import com.econetwireless.utils.messages.AirtimeBalanceResponse;

/**
 * Created by tnyamakura on 17/3/2017.
 */
@FunctionalInterface
public interface EnquiriesService {
    AirtimeBalanceResponse enquire(String partnerCode, String msisdn);
}
