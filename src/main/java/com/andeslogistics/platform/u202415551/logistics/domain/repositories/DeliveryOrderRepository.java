package com.andeslogistics.platform.u202415551.logistics.domain.repositories;

import com.andeslogistics.platform.u202415551.logistics.domain.model.aggregates.DeliveryOrder;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.DeliveryCode;

import java.util.Optional;

public interface DeliveryOrderRepository {
    DeliveryOrder save(DeliveryOrder deliveryOrder);

    boolean existsByDeliveryCode(DeliveryCode deliveryCode);

    Optional<DeliveryOrder> findById(Long id);
}
