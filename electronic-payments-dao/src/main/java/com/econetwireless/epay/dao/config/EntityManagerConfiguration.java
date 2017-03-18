package com.econetwireless.epay.dao.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by tnyamakura on 29/11/2016.
 */
@Configuration
@PropertySource({"classpath:persistance.properties"})
public class EntityManagerConfiguration {

    @Resource
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.econetwireless.epay.domain");
        factoryBean.setJpaProperties(buildJpaProperties());
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return factoryBean;
    }

    private Properties buildJpaProperties() {
        final Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("jpa.generate.ddl"));
        props.setProperty("hibernate.show_sql", environment.getProperty("jpa.debug"));
        return props;
    }

}
