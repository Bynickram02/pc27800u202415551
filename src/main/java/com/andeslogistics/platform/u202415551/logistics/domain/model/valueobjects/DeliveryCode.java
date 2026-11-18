package com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects;

public record DeliveryCode(String code) {
    public DeliveryCode {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Delivery code must not be null or blank");
        }
    }
}
