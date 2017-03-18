package com.econetwireless.epay.business.services.api;

import com.econetwireless.epay.domain.SubscriberRequest;

import java.util.List;

/**
 * Created by tnyamakura on 18/3/2017.
 */
public interface ReportingService {
    List<SubscriberRequest> findSubscriberRequestsByPartnerCode(String partnerCode);
}
