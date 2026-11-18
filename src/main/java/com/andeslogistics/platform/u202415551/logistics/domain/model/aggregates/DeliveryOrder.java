package com.andeslogistics.platform.u202415551.logistics.domain.model.aggregates;

import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.*;
import com.andeslogistics.platform.u202415551.shared.domain.model.AbstractDomainAggregateRoot;
import lombok.Getter;

@Getter
public class DeliveryOrder extends AbstractDomainAggregateRoot {
    private DeliveryCode deliveryCode;
    private WarehouseId warehouseId;
    private DeliveryWindow deliveryWindow;
    private PackageLoad packageLoad;
    private Integer priorityLevel;
    private DeliveryStatus status;
    private String deliveryNotes;

    public DeliveryOrder(DeliveryCode deliveryCode, WarehouseId warehouseId, DeliveryWindow deliveryWindow,
                         PackageLoad packageLoad, Integer priorityLevel, DeliveryStatus status,
                         String deliveryNotes) {
        if (priorityLevel == null || priorityLevel < 1 || priorityLevel > 5) {
            throw new IllegalArgumentException("Priority level must be between 1 and 5");
        }
        this.deliveryCode = deliveryCode;
        this.warehouseId = warehouseId;
        this.deliveryWindow = deliveryWindow;
        this.packageLoad = packageLoad;
        this.priorityLevel = priorityLevel;
        this.status = status == null ? DeliveryStatus.PENDING : status;
        this.deliveryNotes = deliveryNotes;
    }
}
