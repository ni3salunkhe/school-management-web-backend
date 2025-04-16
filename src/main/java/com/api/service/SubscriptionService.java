package com.api.service;

import com.api.dto.SubscriptionDto;
import com.api.entity.Subscription;

import java.time.LocalDate;

public interface SubscriptionService {
    boolean isSubscriptionExpired(long udiseNumber);
    SubscriptionDto renewSubscription(long udiseNumber, LocalDate newEndDate);
    SubscriptionDto createSubscription(long udiseNumber, LocalDate startDate, LocalDate endDate);
    SubscriptionDto getdata(long udiseNumber);  // Changed return type to SubscriptionDto
}
