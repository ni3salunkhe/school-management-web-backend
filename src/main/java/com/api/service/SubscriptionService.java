package com.api.service;

import com.api.dto.SubscriptionDto;
import com.api.entity.Subscription;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionService {
    boolean isSubscriptionExpired(long udiseNumber);
    SubscriptionDto renewSubscription(long udiseNumber, LocalDate newEndDate, long moduleId);
    SubscriptionDto createSubscription(long udiseNumber, LocalDate startDate, LocalDate endDate, long moduleId);
    SubscriptionDto getdata(long udiseNumber);  // Changed return type to SubscriptionDto
	Subscription getExpiringTomorrowByUdise(long udiseNo);
}
