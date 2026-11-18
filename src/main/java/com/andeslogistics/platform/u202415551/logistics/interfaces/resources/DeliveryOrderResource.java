package com.andeslogistics.platform.u202415551.logistics.interfaces.resources;

public record DeliveryOrderResource(
        Long id,
        String deliveryCode,
        String warehouseId,
        String deliveryWindowStartDate,
        String deliveryWindowEndDate,
        Integer packageLoadQuantity,
        String packageLoadUnit,
        Integer priorityLevel,
        String status,
        String deliveryNotes
) {
}
