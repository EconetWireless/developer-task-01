package com.econetwireless.epay.dao.subscriberrequest.api;

import com.econetwireless.epay.domain.SubscriberRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by tnyamakura on 17/3/2017.
 */
public interface SubscriberRequestDao extends JpaRepository<SubscriberRequest, Long> {
    List<SubscriberRequest> findByPartnerCode(@Param("partnerCode") String partnerCode);
}
