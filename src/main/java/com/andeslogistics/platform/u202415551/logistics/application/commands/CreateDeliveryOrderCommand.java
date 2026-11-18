package com.andeslogistics.platform.u202415551.logistics.application.commands;

import java.time.LocalDate;
import java.util.UUID;

public record CreateDeliveryOrderCommand(
        String deliveryCode,
        UUID warehouseId,
        LocalDate deliveryWindowStartDate,
        LocalDate deliveryWindowEndDate,
        Integer packageLoadQuantity,
        String packageLoadUnit,
        Integer priorityLevel,
        String status,
        String deliveryNotes
) {
}
