package com.econetwireless.epay.business.services.api;

import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.messages.AirtimeTopupResponse;

/**
 * Created by tnyamakura on 17/3/2017.
 */
@FunctionalInterface
public interface CreditsService {
    AirtimeTopupResponse credit(AirtimeTopupRequest airtimeTopupRequest);
}
