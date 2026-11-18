package com.andeslogistics.platform.u202415551.logistics.infrastructure.repositories;

import com.andeslogistics.platform.u202415551.logistics.infrastructure.entities.DeliveryOrderPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrderJpaRepository extends JpaRepository<DeliveryOrderPersistenceEntity, Long> {
    boolean existsByDeliveryCode(String deliveryCode);
}
