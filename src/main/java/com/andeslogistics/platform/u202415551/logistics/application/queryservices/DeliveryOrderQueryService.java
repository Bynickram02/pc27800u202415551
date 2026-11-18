package com.andeslogistics.platform.u202415551.logistics.application.queryservices;

import com.andeslogistics.platform.u202415551.logistics.application.queries.GetDeliveryOrderByIdQuery;
import com.andeslogistics.platform.u202415551.logistics.domain.model.aggregates.DeliveryOrder;

import java.util.Optional;

public interface DeliveryOrderQueryService {
    Optional<DeliveryOrder> handle(GetDeliveryOrderByIdQuery query);
}
