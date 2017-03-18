package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.business.services.api.CreditsService;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.constants.SystemConstants;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.messages.AirtimeTopupResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tnyamakura on 17/3/2017.
 */
@Transactional
public class CreditsServiceImpl implements CreditsService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditsServiceImpl.class);

    private ChargingPlatform chargingPlatform;
    private SubscriberRequestDao subscriberRequestDao;

    public CreditsServiceImpl(ChargingPlatform chargingPlatform, SubscriberRequestDao subscriberRequestDao) {
        this.chargingPlatform = chargingPlatform;
        this.subscriberRequestDao = subscriberRequestDao;
    }

    @Override
    public AirtimeTopupResponse credit(final AirtimeTopupRequest airtimeTopupRequest) {
        LOGGER.info("Credit airtime Request : {}", airtimeTopupRequest);
        final AirtimeTopupResponse airtimeTopupResponse = new AirtimeTopupResponse();
        final SubscriberRequest subscriberRequest = populateSubscriberRequest(airtimeTopupRequest);
        final SubscriberRequest createdSubscriberRequest = subscriberRequestDao.persist(subscriberRequest);
        final INCreditResponse inCreditResponse = chargingPlatform.creditSubscriberAccount(populate(airtimeTopupRequest));
        changeSubscriberRequestStatusOnCredit(createdSubscriberRequest, inCreditResponse);
        subscriberRequestDao.update(createdSubscriberRequest);
        airtimeTopupResponse.setResponseCode(inCreditResponse.getResponseCode());
        airtimeTopupResponse.setNarrative(inCreditResponse.getNarrative());
        airtimeTopupResponse.setMsisdn(airtimeTopupRequest.getMsisdn());
        airtimeTopupResponse.setBalance(inCreditResponse.getBalance());
        LOGGER.info("Finished Airtime Credit :: Msisdn : {}, response code : {}", airtimeTopupRequest.getMsisdn(), inCreditResponse.getResponseCode());
        return airtimeTopupResponse;
    }

    private static void changeSubscriberRequestStatusOnCredit(final SubscriberRequest subscriberRequest, final INCreditResponse inCreditResponse) {
        final boolean isSuccessfulResponse = ResponseCode.SUCCESS.getCode().equalsIgnoreCase(inCreditResponse.getResponseCode());
        if(!isSuccessfulResponse) {
            subscriberRequest.setStatus(SystemConstants.STATUS_FAILED);
        } else {
            subscriberRequest.setStatus(SystemConstants.STATUS_SUCCESSFUL);
            subscriberRequest.setBalanceAfter(inCreditResponse.getBalance());
            subscriberRequest.setBalanceBefore(inCreditResponse.getBalance() - subscriberRequest.getAmount());
        }
    }
    private static SubscriberRequest populateSubscriberRequest(final AirtimeTopupRequest airtimeTopupRequest) {
        final SubscriberRequest subscriberRequest = new SubscriberRequest();
        subscriberRequest.setRequestType(SystemConstants.REQUEST_TYPE_AIRTIME_TOPUP);
        subscriberRequest.setPartnerCode(airtimeTopupRequest.getPartnerCode());
        subscriberRequest.setMsisdn(airtimeTopupRequest.getMsisdn());
        subscriberRequest.setReference(airtimeTopupRequest.getReferenceNumber());
        subscriberRequest.setAmount(airtimeTopupRequest.getAmount());
        return subscriberRequest;
    }
    private static INCreditRequest populate(final AirtimeTopupRequest airtimeTopupRequest) {
        final INCreditRequest inCreditRequest = new INCreditRequest();
        inCreditRequest.setAmount(airtimeTopupRequest.getAmount());
        inCreditRequest.setMsisdn(airtimeTopupRequest.getMsisdn());
        inCreditRequest.setPartnerCode(airtimeTopupRequest.getPartnerCode());
        inCreditRequest.setReferenceNumber(airtimeTopupRequest.getReferenceNumber());
        return inCreditRequest;
    }

}
