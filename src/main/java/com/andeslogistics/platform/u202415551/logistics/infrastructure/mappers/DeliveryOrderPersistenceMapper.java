package com.andeslogistics.platform.u202415551.logistics.infrastructure.mappers;

import com.andeslogistics.platform.u202415551.logistics.domain.model.aggregates.DeliveryOrder;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.DeliveryCode;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.DeliveryWindow;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.PackageLoad;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.WarehouseId;
import com.andeslogistics.platform.u202415551.logistics.infrastructure.entities.DeliveryOrderPersistenceEntity;

import java.util.UUID;

public class DeliveryOrderPersistenceMapper {

    public static DeliveryOrderPersistenceEntity toEntity(DeliveryOrder deliveryOrder) {
        var entity = new DeliveryOrderPersistenceEntity();
        entity.setDeliveryCode(deliveryOrder.getDeliveryCode().code());
        entity.setWarehouseId(deliveryOrder.getWarehouseId().value().toString());
        entity.setDeliveryWindowStartDate(deliveryOrder.getDeliveryWindow().startDate());
        entity.setDeliveryWindowEndDate(deliveryOrder.getDeliveryWindow().endDate());
        entity.setPackageLoadQuantity(deliveryOrder.getPackageLoad().quantity());
        entity.setPackageLoadUnit(deliveryOrder.getPackageLoad().unit());
        entity.setPriorityLevel(deliveryOrder.getPriorityLevel());
        entity.setStatus(deliveryOrder.getStatus());
        entity.setDeliveryNotes(deliveryOrder.getDeliveryNotes());
        return entity;
    }

    public static DeliveryOrder toDomain(DeliveryOrderPersistenceEntity entity) {
        var deliveryOrder = new DeliveryOrder(
                new DeliveryCode(entity.getDeliveryCode()),
                new WarehouseId(UUID.fromString(entity.getWarehouseId())),
                new DeliveryWindow(entity.getDeliveryWindowStartDate(), entity.getDeliveryWindowEndDate()),
                new PackageLoad(entity.getPackageLoadQuantity(), entity.getPackageLoadUnit()),
                entity.getPriorityLevel(),
                entity.getStatus(),
                entity.getDeliveryNotes());
        deliveryOrder.setId(entity.getId());
        deliveryOrder.setCreatedAt(entity.getCreatedAt());
        deliveryOrder.setUpdatedAt(entity.getUpdatedAt());
        return deliveryOrder;
    }
}
