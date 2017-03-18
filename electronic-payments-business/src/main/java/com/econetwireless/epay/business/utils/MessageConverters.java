package com.econetwireless.epay.business.utils;

import com.econetwireless.in.webservice.BalanceResponse;
import com.econetwireless.in.webservice.CreditRequest;
import com.econetwireless.in.webservice.CreditResponse;
import com.econetwireless.utils.pojo.INBalanceResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;

/**
 * Created by tnyamakura on 17/3/2017.
 */
public class MessageConverters {

    private MessageConverters() {
        super();
    }

    public static CreditRequest convert(final INCreditRequest inCreditRequest) {
        if(inCreditRequest == null) {
            return null;
        }
        final CreditRequest  creditRequest = new CreditRequest();
        creditRequest.setMsisdn(inCreditRequest.getMsisdn());
        creditRequest.setAmount(inCreditRequest.getAmount());
        creditRequest.setPartnerCode(inCreditRequest.getPartnerCode());
        creditRequest.setReferenceNumber(inCreditRequest.getReferenceNumber());
        return creditRequest;
    }

    public static INCreditRequest convert(final CreditRequest creditRequest) {
        if(creditRequest == null) {
            return null;
        }
        final INCreditRequest  inCreditRequest = new INCreditRequest();
        inCreditRequest.setMsisdn(creditRequest.getMsisdn());
        inCreditRequest.setAmount(creditRequest.getAmount());
        inCreditRequest.setPartnerCode(creditRequest.getPartnerCode());
        inCreditRequest.setReferenceNumber(creditRequest.getReferenceNumber());
        return inCreditRequest;
    }

    public static CreditResponse convert(final INCreditResponse inCreditResponse) {
        if(inCreditResponse == null) {
            return null;
        }
        final CreditResponse  creditResponse = new CreditResponse();
        creditResponse.setNarrative(inCreditResponse.getNarrative());
        creditResponse.setMsisdn(inCreditResponse.getMsisdn());
        creditResponse.setBalance(inCreditResponse.getBalance());
        creditResponse.setResponseCode(inCreditResponse.getResponseCode());
        return creditResponse;
    }

    public static INCreditResponse convert(final CreditResponse creditResponse) {
        if(creditResponse == null) {
            return null;
        }
        final INCreditResponse  inCreditResponse = new INCreditResponse();
        inCreditResponse.setNarrative(creditResponse.getNarrative());
        inCreditResponse.setMsisdn(creditResponse.getMsisdn());
        inCreditResponse.setBalance(creditResponse.getBalance());
        inCreditResponse.setResponseCode(creditResponse.getResponseCode());
        return inCreditResponse;
    }

    public static BalanceResponse convert(final INBalanceResponse inBalanceResponse) {
        if(inBalanceResponse == null) {
            return null;
        }
        final BalanceResponse  balanceResponse = new BalanceResponse();
        balanceResponse.setNarrative(inBalanceResponse.getNarrative());
        balanceResponse.setMsisdn(inBalanceResponse.getMsisdn());
        balanceResponse.setAmount(inBalanceResponse.getAmount());
        balanceResponse.setResponseCode(inBalanceResponse.getResponseCode());
        return balanceResponse;
    }

    public static INBalanceResponse convert(final BalanceResponse balanceResponse) {
        if(balanceResponse == null) {
            return null;
        }
        final INBalanceResponse  inBalanceResponse = new INBalanceResponse();
        inBalanceResponse.setNarrative(balanceResponse.getNarrative());
        inBalanceResponse.setMsisdn(balanceResponse.getMsisdn());
        inBalanceResponse.setAmount(balanceResponse.getAmount());
        inBalanceResponse.setResponseCode(balanceResponse.getResponseCode());
        return inBalanceResponse;
    }
}
