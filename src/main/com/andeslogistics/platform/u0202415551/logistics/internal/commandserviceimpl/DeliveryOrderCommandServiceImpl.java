package com.andeslogistics.platform.u202415551.logistics.application.internal.commandserviceimpl;

import com.andeslogistics.platform.u202415551.logistics.application.commands.CreateDeliveryOrderCommand;
import com.andeslogistics.platform.u202415551.logistics.application.commandservices.DeliveryOrderCommandService;
import com.andeslogistics.platform.u202415551.logistics.domain.model.aggregates.DeliveryOrder;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.DeliveryCode;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.DeliveryStatus;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.DeliveryWindow;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.PackageLoad;
import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.WarehouseId;
import com.andeslogistics.platform.u202415551.logistics.domain.repositories.DeliveryOrderRepository;
import com.andeslogistics.platform.u202415551.shared.domain.result.ErrorType;
import com.andeslogistics.platform.u202415551.shared.domain.result.Result;
import org.springframework.stereotype.Service;

@Service
public class DeliveryOrderCommandServiceImpl implements DeliveryOrderCommandService {

    private final DeliveryOrderRepository repository;

    public DeliveryOrderCommandServiceImpl(DeliveryOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<DeliveryOrder> handle(CreateDeliveryOrderCommand command) {
        try {
            var deliveryCode = new DeliveryCode(command.deliveryCode());
            if (repository.existsByDeliveryCode(deliveryCode)) {
                return new Result.Failure<>(ErrorType.DUPLICATE, "delivery.order.duplicate");
            }
            var status = command.status() == null
                    ? DeliveryStatus.PENDING
                    : DeliveryStatus.valueOf(command.status());
            var deliveryOrder = new DeliveryOrder(
                    deliveryCode,
                    new WarehouseId(command.warehouseId()),
                    new DeliveryWindow(command.deliveryWindowStartDate(), command.deliveryWindowEndDate()),
                    new PackageLoad(command.packageLoadQuantity(), command.packageLoadUnit()),
                    command.priorityLevel(),
                    status,
                    command.deliveryNotes());
            return new Result.Success<>(repository.save(deliveryOrder));
        } catch (IllegalArgumentException exception) {
            return new Result.Failure<>(ErrorType.INVALID, exception.getMessage());
        }
    }
}
