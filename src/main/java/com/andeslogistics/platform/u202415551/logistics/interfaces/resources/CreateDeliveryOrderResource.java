package com.andeslogistics.platform.u202415551.logistics.interfaces.resources;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record CreateDeliveryOrderResource(
        @NotBlank
        String deliveryCode,
        @NotNull
        UUID warehouseId,
        @NotNull
        LocalDate deliveryWindowStartDate,
        @NotNull
        LocalDate deliveryWindowEndDate,
        @NotNull
        @Positive
        Integer packageLoadQuantity,
        @NotBlank
        String packageLoadUnit,
        @NotNull
        @Min(1)
        @Max(5)
        Integer priorityLevel,
        String status,
        String deliveryNotes
) {
}
