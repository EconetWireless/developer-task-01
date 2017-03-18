package com.econetwireless.epay.business.config;

import com.econetwireless.epay.dao.config.DaoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by tnyamakura on 17/3/2017.
 */
@Configuration
@Import({DaoConfiguration.class, IntegrationsConfig.class, EpayBusinessConfig.class})
public class RootConfig {


}
