package com.econetwireless.in.soap.service;

import com.econetwireless.in.soap.messages.BalanceResponse;
import com.econetwireless.in.soap.messages.CreditRequest;
import com.econetwireless.in.soap.messages.CreditResponse;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by tnyamakura on 17/3/2017.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface IntelligentNetworkService {
    BalanceResponse enquireBalance(String partnerCode, @WebParam(name = "msisdn") String msisdn);
    CreditResponse creditSubscriberAccount(@WebParam(name = "creditRequest") CreditRequest creditRequest);

}
