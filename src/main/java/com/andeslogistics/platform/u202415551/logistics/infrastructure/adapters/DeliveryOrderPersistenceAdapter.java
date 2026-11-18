package com.andeslogistics.platform.u202415551.logistics.infrastructure.adapters;

import com.andeslogistics.platform.u202415551.logistics.domain.model.aggregates.DeliveryOrder;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.DeliveryCode;
import com.andeslogistics.platform.u202415551.logistics.domain.repositories.DeliveryOrderRepository;
import com.andeslogistics.platform.u202415551.logistics.infrastructure.mappers.DeliveryOrderPersistenceMapper;
import com.andeslogistics.platform.u202415551.logistics.infrastructure.repositories.DeliveryOrderJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeliveryOrderPersistenceAdapter implements DeliveryOrderRepository {

    private final DeliveryOrderJpaRepository repository;

    public DeliveryOrderPersistenceAdapter(DeliveryOrderJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeliveryOrder save(DeliveryOrder deliveryOrder) {
        var saved = repository.save(DeliveryOrderPersistenceMapper.toEntity(deliveryOrder));
        return DeliveryOrderPersistenceMapper.toDomain(saved);
    }

    @Override
    public boolean existsByDeliveryCode(DeliveryCode deliveryCode) {
        return repository.existsByDeliveryCode(deliveryCode.code());
    }

    @Override
    public Optional<DeliveryOrder> findById(Long id) {
        return Optional.empty();
    }
}
