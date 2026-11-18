package com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects;

public recordPackageLoad(Integer quantity, String unit) {
    public PackageLoad {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (unit == null || unit.isBlank()) {
            throw new IllegalArgumentException("Unit must not be null or blank");
        }
    }

    public PackageLoad increaseBy(Integer additionalQuantity) {
        if (additionalQuantity == null || additionalQuantity <= 0) {
            throw new IllegalArgumentException("Additional quantity must be greater than zero");
        }
        return new PackageLoad(quantity + additionalQuantity, unit);
    }
}
