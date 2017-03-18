package com.econetwireless.epay.business.config;

import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.business.services.api.CreditsService;
import com.econetwireless.epay.business.services.api.EnquiriesService;
import com.econetwireless.epay.business.services.api.PartnerCodeValidator;
import com.econetwireless.epay.business.services.api.ReportingService;
import com.econetwireless.epay.business.services.impl.CreditsServiceImpl;
import com.econetwireless.epay.business.services.impl.EnquiriesServiceImpl;
import com.econetwireless.epay.business.services.impl.PartnerCodeValidatorImpl;
import com.econetwireless.epay.business.services.impl.ReportingServiceImpl;
import com.econetwireless.epay.dao.requestpartner.api.RequestPartnerDao;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tnyamakura on 17/3/2017.
 */
@Configuration
public class EpayBusinessConfig {

    @Bean
    public CreditsService creditsService(final ChargingPlatform chargingPlatform, final SubscriberRequestDao subscriberRequestDao) {
        return new CreditsServiceImpl(chargingPlatform, subscriberRequestDao);
    }

    @Bean
    public EnquiriesService enquiriesService(final ChargingPlatform chargingPlatform, final SubscriberRequestDao subscriberRequestDao) {
        return new EnquiriesServiceImpl(chargingPlatform, subscriberRequestDao);
    }

    @Bean
    public ReportingService reportingService(final SubscriberRequestDao subscriberRequestDao) {
        return new ReportingServiceImpl(subscriberRequestDao);
    }

    @Bean
    public PartnerCodeValidator partnerCodeValidator(final RequestPartnerDao requestPartnerDao) {
        return  new PartnerCodeValidatorImpl(requestPartnerDao);
    }
}
