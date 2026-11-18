package com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects;

import java.time.LocalDate;

public record DeliveryWindow(LocalDate startDate, LocalDate endDate) {
    public DeliveryWindow {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Delivery window dates must not be null");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
    }

    public boolean includes(LocalDate checkDate) {
        if (checkDate == null) {
            throw new IllegalArgumentException("Check date must not be null");
        }
        return !checkDate.isBefore(startDate) && !checkDate.isAfter(endDate);
    }
}
