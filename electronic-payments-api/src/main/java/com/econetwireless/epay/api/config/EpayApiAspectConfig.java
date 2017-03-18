package com.econetwireless.epay.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by tnyamakura on 30/11/2016.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.econetwireless.epay.api.aspects"})
public class EpayApiAspectConfig {
}
