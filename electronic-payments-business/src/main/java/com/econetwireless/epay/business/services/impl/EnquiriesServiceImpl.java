package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.business.services.api.EnquiriesService;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.constants.SystemConstants;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.messages.AirtimeBalanceResponse;
import com.econetwireless.utils.pojo.INBalanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tnyamakura on 17/3/2017.
 */
@Transactional
public class EnquiriesServiceImpl implements EnquiriesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnquiriesServiceImpl.class);

    private ChargingPlatform chargingPlatform;
    private SubscriberRequestDao subscriberRequestDao;

    public EnquiriesServiceImpl(ChargingPlatform chargingPlatform, SubscriberRequestDao subscriberRequestDao) {
        this.chargingPlatform = chargingPlatform;
        this.subscriberRequestDao = subscriberRequestDao;
    }

    @Override
    public AirtimeBalanceResponse enquire(final String partnerCode, final String msisdn) {
        LOGGER.info("Enquire airtime balance :: Partner Code : {}, Msisdn : {}", partnerCode, msisdn);
        final AirtimeBalanceResponse airtimeBalanceResponse = new AirtimeBalanceResponse();
        final SubscriberRequest subscriberRequest = populate(partnerCode, msisdn);
        final SubscriberRequest createdSubscriberRequest = subscriberRequestDao.persist(subscriberRequest);
        final INBalanceResponse inBalanceResponse = chargingPlatform.enquireBalance(partnerCode, msisdn);
        changeSubscriberStateOnBalanceEnquiry(createdSubscriberRequest, inBalanceResponse);
        subscriberRequestDao.update(createdSubscriberRequest);
        airtimeBalanceResponse.setResponseCode(inBalanceResponse.getResponseCode());
        airtimeBalanceResponse.setNarrative(inBalanceResponse.getNarrative());
        airtimeBalanceResponse.setMsisdn(msisdn);
        airtimeBalanceResponse.setAmount(inBalanceResponse.getAmount());
        LOGGER.info("Finished balance enquiry :: Msisdn : {}, response code : {}", msisdn, inBalanceResponse.getResponseCode());
        return airtimeBalanceResponse;
    }

    private static void changeSubscriberStateOnBalanceEnquiry(final SubscriberRequest subscriberRequest, final INBalanceResponse inBalanceResponse) {
        final boolean isSuccessfulResponse = ResponseCode.SUCCESS.getCode().equalsIgnoreCase(inBalanceResponse.getResponseCode());
        if(!isSuccessfulResponse) {
            subscriberRequest.setStatus(SystemConstants.STATUS_FAILED);
        } else {
            subscriberRequest.setStatus(SystemConstants.STATUS_SUCCESSFUL);
            subscriberRequest.setBalanceAfter(inBalanceResponse.getAmount());
            subscriberRequest.setBalanceBefore(inBalanceResponse.getAmount());
        }
    }
    private static SubscriberRequest populate(final String partnerCode, final String msisdn) {
        final SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setRequestType(SystemConstants.REQUEST_TYPE_AIRTIME_BALANCE_ENQUIRY);
        subscriberRequest.setPartnerCode(partnerCode);
        subscriberRequest.setMsisdn(msisdn);
        return  subscriberRequest;
    }

}
