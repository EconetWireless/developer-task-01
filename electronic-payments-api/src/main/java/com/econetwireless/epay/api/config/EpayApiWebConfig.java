package com.econetwireless.epay.api.config;

import com.econetwireless.epay.api.processors.api.EpayRequestProcessor;
import com.econetwireless.epay.api.processors.api.ReportingProcessor;
import com.econetwireless.epay.api.processors.impl.EpayRequestProcessorImpl;
import com.econetwireless.epay.api.processors.impl.ReportingProcessorImpl;
import com.econetwireless.epay.business.services.api.CreditsService;
import com.econetwireless.epay.business.services.api.EnquiriesService;
import com.econetwireless.epay.business.services.api.ReportingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by tnyamakura on 30/11/2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.econetwireless.epay.api.rest.resources",
        })
@Import({EpayApiAspectConfig.class})
public class EpayApiWebConfig {

        @Bean
        public EpayRequestProcessor epayRequestProcessor(final EnquiriesService enquiriesService, final CreditsService creditsService) {
                return new EpayRequestProcessorImpl(enquiriesService, creditsService);
        }

        @Bean
        public ReportingProcessor reportingProcessor(final ReportingService reportingService) {
                return new ReportingProcessorImpl(reportingService);
        }


}
