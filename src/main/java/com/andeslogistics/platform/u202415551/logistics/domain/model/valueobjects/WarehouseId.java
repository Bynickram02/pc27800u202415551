package com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects;

import java.util.UUID;

public record WarehouseId(UUID value) {
    public WarehouseId {
        if (value == null) {
            throw new IllegalArgumentException("Warehouse id must not be null");
        }
    }
}
