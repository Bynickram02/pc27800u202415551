package com.andeslogistics.platform.u202415551.logistics.application.internal.queryserviceimpl;

import com.andeslogistics.platform.u202415551.logistics.application.queries.GetDeliveryOrderByIdQuery;
import com.andeslogistics.platform.u202415551.logistics.application.queryservices.DeliveryOrderQueryService;
import com.andeslogistics.platform.u202415551.logistics.domain.model.aggregates.DeliveryOrder;
import com.andeslogistics.platform.u202415551.logistics.domain.repositories.DeliveryOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryOrderQueryServiceImpl implements DeliveryOrderQueryService {

    private final DeliveryOrderRepository repository;

    public DeliveryOrderQueryServiceImpl(DeliveryOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DeliveryOrder> handle(GetDeliveryOrderByIdQuery query) {
        return repository.findById(query.id());
    }
}
