package com.econetwireless.in.soap.service;

import com.econetwireless.in.soap.messages.BalanceResponse;
import com.econetwireless.in.soap.messages.CreditRequest;
import com.econetwireless.in.soap.messages.CreditResponse;
import com.econetwireless.utils.enums.ResponseCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * Created by tnyamakura on 17/3/2017.
 */
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
@WebService(endpointInterface = "com.econetwireless.in.soap.service.IntelligentNetworkService",
        serviceName = "IntelligentNetworkServiceImpl", portName = "IntelligentNetworkPort", name = "IntelligentNetworkService")
public class IntelligentNetworkServiceImpl implements IntelligentNetworkService{

    private static final Logger LOGGER = LoggerFactory.getLogger(IntelligentNetworkServiceImpl.class);
    @Override
    public BalanceResponse enquireBalance(final String partnerCode, final String msisdn) {
        final BalanceResponse balanceResponse = new BalanceResponse();
        if(StringUtils.isEmpty(partnerCode)) {
            balanceResponse.setResponseCode(ResponseCode.INVALID_REQUEST.getCode());
            balanceResponse.setNarrative("Invalid request, missing partner code");
            return balanceResponse;
        }
        if(StringUtils.isEmpty(msisdn)) {
            balanceResponse.setResponseCode(ResponseCode.INVALID_REQUEST.getCode());
            balanceResponse.setNarrative("Invalid request, missing mobile number");
            return balanceResponse;
        }
        balanceResponse.setMsisdn(msisdn);
        balanceResponse.setAmount(getRandomBalance());
        balanceResponse.setNarrative("Successful balance enquiry");
        balanceResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        LOGGER.info("Msisdn : {}, Balance : {}", msisdn, balanceResponse.getAmount());
        return balanceResponse;
    }

    @Override
    public CreditResponse creditSubscriberAccount(final CreditRequest creditRequest) {
        final CreditResponse creditResponse = new CreditResponse();
        if(creditRequest == null) {
            creditResponse.setResponseCode(ResponseCode.FAILED.getCode());
            creditResponse.setNarrative("Invalid request, empty credit request");
            return creditResponse;
        }
        creditResponse.setMsisdn(creditRequest.getMsisdn());
        creditResponse.setBalance(creditRequest.getAmount() + getRandomBalance());
        creditResponse.setNarrative("Successful credit request");
        creditResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        return creditResponse;
    }

    private static double getRandomBalance() {
        final int randomCount = Integer.parseInt(RandomStringUtils.random(1, "123"));
        return Double.parseDouble(RandomStringUtils.random(randomCount, "123456789"));
    }
}
