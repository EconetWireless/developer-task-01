package com.econetwireless.epay.api.rest.messages;

import com.econetwireless.epay.domain.SubscriberRequest;

import java.util.List;

/**
 * Created by tnyamakura on 18/3/2017.
 */
public class TransactionsResponse {
    private String responseCode;
    private String narrative;
    private String partnerCode;
    private List<SubscriberRequest> subscriberRequests;


    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public List<SubscriberRequest> getSubscriberRequests() {
        return subscriberRequests;
    }

    public void setSubscriberRequests(List<SubscriberRequest> subscriberRequests) {
        this.subscriberRequests = subscriberRequests;
    }
}
