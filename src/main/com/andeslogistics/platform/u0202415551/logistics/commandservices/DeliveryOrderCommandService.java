package com.andeslogistics.platform.u202415551.logistics.application.commandservices;

import com.andeslogistics.platform.u202415551.logistics.application.commands.CreateDeliveryOrderCommand;
import com.andeslogistics.platform.u202415551.logistics.domain.model.aggregates.DeliveryOrder;
import com.andeslogistics.platform.u202415551.shared.domain.result.Result;

public interface DeliveryOrderCommandService {
    Result<DeliveryOrder> handle(CreateDeliveryOrderCommand command);
}
