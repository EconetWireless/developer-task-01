package com.econetwireless.epay.dao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by tnyamakura on 29/11/2016.
 */
@Configuration
@EnableJpaRepositories({"com.econetwireless.epay.dao.subscriberrequest.api",
        "com.econetwireless.epay.dao.requestpartner.api"})
@Import({DataSourceConfiguration.class, EntityManagerConfiguration.class, TransactionManagementConfiguration.class})
public class DaoConfiguration {
}
