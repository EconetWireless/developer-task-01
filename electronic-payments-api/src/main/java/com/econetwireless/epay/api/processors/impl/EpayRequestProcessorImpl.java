package com.econetwireless.epay.api.processors.impl;

import com.econetwireless.epay.api.processors.api.EpayRequestProcessor;
import com.econetwireless.epay.business.services.api.CreditsService;
import com.econetwireless.epay.business.services.api.EnquiriesService;
import com.econetwireless.utils.formatters.MobileNumberUtils;
import com.econetwireless.utils.messages.AirtimeBalanceResponse;
import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.messages.AirtimeTopupResponse;

/**
 * Created by tnyamakura on 18/3/2017.
 */
public class EpayRequestProcessorImpl implements EpayRequestProcessor{

    private EnquiriesService enquiriesService;
    private CreditsService creditsService;

    public EpayRequestProcessorImpl(EnquiriesService enquiriesService, CreditsService creditsService) {
        this.enquiriesService = enquiriesService;
        this.creditsService = creditsService;
    }

    @Override
    public AirtimeBalanceResponse enquireAirtimeBalance(final String partnerCode, final String msisdn) {
        return enquiriesService.enquire(partnerCode, MobileNumberUtils.formatMobileToInternationalMode(msisdn));
    }

    @Override
    public AirtimeTopupResponse creditAirtime(final AirtimeTopupRequest airtimeTopupRequest) {
        airtimeTopupRequest.setMsisdn(MobileNumberUtils.formatMobileToInternationalMode(airtimeTopupRequest.getMsisdn()));
        return creditsService.credit(airtimeTopupRequest);
    }
}
