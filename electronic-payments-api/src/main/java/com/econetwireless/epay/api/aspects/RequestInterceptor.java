package com.econetwireless.epay.api.aspects;

import com.econetwireless.epay.api.rest.messages.TransactionsResponse;
import com.econetwireless.epay.business.services.api.PartnerCodeValidator;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.execeptions.EpayException;
import com.econetwireless.utils.formatters.ResponseMarker;
import com.econetwireless.utils.formatters.UtilityBuilder;
import com.econetwireless.utils.messages.AirtimeBalanceResponse;
import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.messages.AirtimeTopupResponse;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tnyamakura on 18/3/2017.
 */
@Component
@Aspect
public class RequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    @Autowired
    private PartnerCodeValidator partnerCodeValidator;

    @Around("execution(* com.econetwireless.epay.api.rest.resources.EpayResource.getPartnerTransactions(..)) and args(partnerCode)")
    public TransactionsResponse getPartnerTransactions(final ProceedingJoinPoint joinPoint, final String partnerCode) {
        TransactionsResponse transactionsResponse = new TransactionsResponse();
        try {
            LOGGER.info("IN Get Partner Transactions :: Partner Code : {}",
                    partnerCode);
            transactionsResponse = checkingMissingFields(partnerCode);
            if(StringUtils.isNotEmpty(transactionsResponse.getResponseCode())) {
                return transactionsResponse;
            }
            partnerCodeValidator.validatePartnerCode(partnerCode);
            final Object response = joinPoint.proceed();
            transactionsResponse = (TransactionsResponse) response;
        } catch (EpayException ee) {
            LOGGER.error("Custom Error on get transactions by partner code : ", ee);
            transactionsResponse.setNarrative(ee.getMessage());
            transactionsResponse.setResponseCode(ee.getResponseCode().getCode());
        }catch (Throwable throwable) {
            LOGGER.error("Error on get transactions by partner code : ", throwable);
            transactionsResponse.setNarrative("Balance enquiry request failed, please try again later");
            transactionsResponse.setResponseCode(ResponseCode.FAILED.getCode());

        }
        LOGGER.info("[{}] IN Get Partner Transactions Response :: Partner Code : {}, Response Code : {}, Narrative : {}",
                ResponseMarker.getMarker(transactionsResponse.getResponseCode()), partnerCode,
                transactionsResponse.getResponseCode(), transactionsResponse.getNarrative());
        return  transactionsResponse;
    }


    @Around("execution(* com.econetwireless.epay.api.rest.resources.EpayResource.enquireAirtimeBalance(..)) and args(partnerCode, msisdn)")
    public AirtimeBalanceResponse enquireAirtimeBalance(final ProceedingJoinPoint joinPoint, final String partnerCode, final String msisdn) {
        AirtimeBalanceResponse airtimeBalanceResponse = new AirtimeBalanceResponse();
        try {
            LOGGER.info("IN Enquire Airtime Balance :: Partner Code : {}, Mobile Number : {}",
                    partnerCode, msisdn);
            airtimeBalanceResponse = checkingMissingFields(partnerCode, msisdn);
            if(StringUtils.isNotEmpty(airtimeBalanceResponse.getResponseCode())) {
                return airtimeBalanceResponse;
            }
            partnerCodeValidator.validatePartnerCode(partnerCode);
            final Object response = joinPoint.proceed();
            airtimeBalanceResponse = (AirtimeBalanceResponse) response;
        } catch (EpayException ee) {
            LOGGER.error("Custom Error on airtime balance : ", ee);
            airtimeBalanceResponse.setNarrative(ee.getMessage());
            airtimeBalanceResponse.setResponseCode(ee.getResponseCode().getCode());
        }catch (Throwable throwable) {
            LOGGER.error("Error on balance enquiry : ", throwable);
            airtimeBalanceResponse.setNarrative("Balance enquiry request failed, please try again later");
            airtimeBalanceResponse.setResponseCode(ResponseCode.FAILED.getCode());

        }
        LOGGER.info("[{}] IN Enquire Airtime Balance Response :: Partner Code : {}, Mobile Number : {}, Response Code : {}, Narrative : {}",
                ResponseMarker.getMarker(airtimeBalanceResponse.getResponseCode()), partnerCode, msisdn,
                airtimeBalanceResponse.getResponseCode(), airtimeBalanceResponse.getNarrative());
        return  airtimeBalanceResponse;
    }

    @Around("execution(* com.econetwireless.epay.api.rest.resources.EpayResource.creditAirtime(..)) and args(airtimeTopupRequest)")
    public AirtimeTopupResponse creditAirtime(final ProceedingJoinPoint joinPoint, final AirtimeTopupRequest airtimeTopupRequest) {
        AirtimeTopupResponse airtimeTopupResponse = new AirtimeTopupResponse();
        try {
            LOGGER.info("IN ECredit Airtime :: Partner Code : {}, Mobile Number : {}",
                    airtimeTopupRequest.getPartnerCode(), airtimeTopupRequest.getPartnerCode());
            airtimeTopupResponse = checkingMissingFields(airtimeTopupRequest);
            if(StringUtils.isNotEmpty(airtimeTopupResponse.getResponseCode())) {
                return airtimeTopupResponse;
            }
            partnerCodeValidator.validatePartnerCode(airtimeTopupRequest.getPartnerCode());
            final Object response = joinPoint.proceed();
            airtimeTopupResponse = (AirtimeTopupResponse) response;
        } catch (EpayException ee) {
            LOGGER.error("Custom Error on airtime credit : ", ee);
            airtimeTopupResponse.setNarrative(ee.getMessage());
            airtimeTopupResponse.setResponseCode(ee.getResponseCode().getCode());
        }catch (Throwable throwable) {
            LOGGER.error("Error on credit airtime : ", throwable);
            airtimeTopupResponse.setNarrative("Airtime Purchase request failed, please try again later");
            airtimeTopupResponse.setResponseCode(ResponseCode.FAILED.getCode());
        }
        LOGGER.info("[{}] IN Airtime Credit Response :: Partner Code : {}, Mobile Number : {}, Response Code : {}, Narrative : {}",
                ResponseMarker.getMarker(airtimeTopupResponse.getResponseCode()), airtimeTopupRequest.getPartnerCode(), airtimeTopupRequest.getPartnerCode(),
                airtimeTopupResponse.getResponseCode(), airtimeTopupResponse.getNarrative());
        return  airtimeTopupResponse;
    }


    private static AirtimeTopupResponse checkingMissingFields(final AirtimeTopupRequest airtimeTopupRequest) {
        final AirtimeTopupResponse airtimeTopupResponse = new AirtimeTopupResponse();
        airtimeTopupResponse.setMsisdn(airtimeTopupRequest.getMsisdn());
        final StringBuilder builder = new StringBuilder();
        UtilityBuilder.buildErrorMessage(builder, StringUtils.isEmpty(airtimeTopupRequest.getReferenceNumber()), "Reference Number is required");
        UtilityBuilder.buildErrorMessage(builder, airtimeTopupRequest.getAmount() <= 0, "Recharge amount is required");
        validatePartnerCode(builder, airtimeTopupRequest.getPartnerCode());
        validateMsisdn(builder, airtimeTopupRequest.getMsisdn());
        if(builder.length() > 0) {
            airtimeTopupResponse.setNarrative(builder.toString());
            airtimeTopupResponse.setResponseCode(ResponseCode.INVALID_REQUEST.getCode());
        }
        return  airtimeTopupResponse;

    }
    private static AirtimeBalanceResponse checkingMissingFields(final String partnerCode, final String msisdn) {
        final AirtimeBalanceResponse airtimeBalanceResponse = new AirtimeBalanceResponse();
        airtimeBalanceResponse.setMsisdn(msisdn);
        final StringBuilder builder = new StringBuilder();
        validatePartnerCode(builder, partnerCode);
        validateMsisdn(builder, msisdn);
        if(builder.length() > 0) {
            airtimeBalanceResponse.setNarrative(builder.toString());
            airtimeBalanceResponse.setResponseCode(ResponseCode.INVALID_REQUEST.getCode());
        }
        return  airtimeBalanceResponse;

    }

    private static TransactionsResponse checkingMissingFields(final String partnerCode) {
        final TransactionsResponse transactionsResponse = new TransactionsResponse();
        transactionsResponse.setPartnerCode(partnerCode);
        final StringBuilder builder = new StringBuilder();
        validatePartnerCode(builder, partnerCode);
        if(builder.length() > 0) {
            transactionsResponse.setNarrative(builder.toString());
            transactionsResponse.setResponseCode(ResponseCode.INVALID_REQUEST.getCode());
        }
        return  transactionsResponse;

    }

    private static void validatePartnerCode(final StringBuilder builder, final String partnerCode) {
        UtilityBuilder.buildErrorMessage(builder, StringUtils.isEmpty(partnerCode), "Partner Code is required");

    }

    private static void validateMsisdn(final StringBuilder builder, final String msisdn) {
        UtilityBuilder.buildErrorMessage(builder, StringUtils.isEmpty(msisdn), "Mobile Number is required");
    }
}
