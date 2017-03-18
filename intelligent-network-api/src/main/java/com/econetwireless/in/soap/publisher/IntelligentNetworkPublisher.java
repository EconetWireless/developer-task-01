package com.econetwireless.in.soap.publisher;

import com.econetwireless.in.soap.service.IntelligentNetworkServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Endpoint;

/**
 * Created by tnyamakura on 17/3/2017.
 */
public class IntelligentNetworkPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntelligentNetworkPublisher.class);

    private IntelligentNetworkPublisher() {
        super();
    }

    public static void  main(String ... publishingParameters) {
        final String endpointUrl = "http://localhost:8888/intelligent-network-api/IntelligentNetworkService";
        LOGGER.info("Starting Intelligent network service....");
        Endpoint.publish(endpointUrl, new IntelligentNetworkServiceImpl());
        LOGGER.info("Finished publishing WS");
    }
}
