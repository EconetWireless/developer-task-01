package com.econetwireless.epay.business.config;

import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.business.integrations.impl.ChargingPlatformImpl;
import com.econetwireless.in.webservice.IntelligentNetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

/**
 * Created by tnyamakura on 17/3/2017.
 */
@Configuration
@PropertySource("classpath:econet-ws-application.properties")
public class IntegrationsConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationsConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public ChargingPlatform chargingPlatform(final IntelligentNetworkService intelligentNetworkService) {
        return new ChargingPlatformImpl(intelligentNetworkService);
    }

    @Bean
    public JaxWsPortProxyFactoryBean intelligentNetworkService() {
        final JaxWsPortProxyFactoryBean proxyFactoryBean = new JaxWsPortProxyFactoryBean();
        try {
            if(env == null) {
                return proxyFactoryBean;
            }
            final Class<?> serviceInterface = Class.forName(env.getProperty("configs.econetwebservice.ws.serviceInterface"));
            proxyFactoryBean.setServiceInterface(serviceInterface);
            Resource resource = new ClassPathResource(env.getProperty("configs.econetwebservice.ws.wsdl"));
            LOGGER.info("intelligentNetworkService URL : {}", resource.getURL());
            proxyFactoryBean.setWsdlDocumentUrl(resource.getURL());
            proxyFactoryBean.setNamespaceUri(env.getProperty("configs.econetwebservice.ws.namespace"));
            proxyFactoryBean.setServiceName(env.getProperty("configs.econetwebservice.ws.serviceName"));
            proxyFactoryBean.setPortName(env.getProperty("configs.econetwebservice.ws.portName"));
            proxyFactoryBean.setEndpointAddress(env.getProperty("configs.econetwebservice.ws.endpoint.address"));
            proxyFactoryBean.setLookupServiceOnStartup(false);
            return proxyFactoryBean;
        }  catch (Exception e) {
            LOGGER.error("Error Creating intelligentNetworkService : ", e);
            return null;
        }
    }

}
