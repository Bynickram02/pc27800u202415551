package com.andeslogistics.platform.u202415551.logistics.interfaces.assemblers;

import com.andeslogistics.platform.u202415551.logistics.application.commands.CreateDeliveryOrderCommand;
import com.andeslogistics.platform.u202415551.logistics.domain.model.aggregates.DeliveryOrder;
import com.andeslogistics.platform.u202415551.logistics.interfaces.resources.CreateDeliveryOrderResource;
import com.andeslogistics.platform.u202415551.logistics.interfaces.resources.DeliveryOrderResource;

public class DeliveryOrderResourceAssembler {

    public static CreateDeliveryOrderCommand toCommandFromResource(CreateDeliveryOrderResource resource) {
        return new CreateDeliveryOrderCommand(
                resource.deliveryCode(),
                resource.warehouseId(),
                resource.deliveryWindowStartDate(),
                resource.deliveryWindowEndDate(),
                resource.packageLoadQuantity(),
                resource.packageLoadUnit(),
                resource.priorityLevel(),
                resource.status(),
                resource.deliveryNotes());
    }

    public static DeliveryOrderResource toResourceFromEntity(DeliveryOrder deliveryOrder) {
        return new DeliveryOrderResource(
                deliveryOrder.getId(),
                deliveryOrder.getDeliveryCode().code(),
                deliveryOrder.getWarehouseId().value().toString(),
                deliveryOrder.getDeliveryWindow().startDate().toString(),
                deliveryOrder.getDeliveryWindow().endDate().toString(),
                deliveryOrder.getPackageLoad().quantity(),
                deliveryOrder.getPackageLoad().unit(),
                deliveryOrder.getPriorityLevel(),
                deliveryOrder.getStatus().name(),
                deliveryOrder.getDeliveryNotes());
    }
}
