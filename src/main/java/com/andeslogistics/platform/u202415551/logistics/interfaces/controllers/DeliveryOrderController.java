package com.andeslogistics.platform.u202415551.logistics.interfaces.controllers;

import com.andeslogistics.platform.u202415551.logistics.application.commandservices.DeliveryOrderCommandService;
import com.andeslogistics.platform.u202415551.logistics.application.queries.GetDeliveryOrderByIdQuery;
import com.andeslogistics.platform.u202415551.logistics.application.queryservices.DeliveryOrderQueryService;
import com.andeslogistics.platform.u202415551.logistics.domain.model.aggregates.DeliveryOrder;
import com.andeslogistics.platform.u202415551.logistics.interfaces.assemblers.DeliveryOrderResourceAssembler;
import com.andeslogistics.platform.u202415551.logistics.interfaces.resources.CreateDeliveryOrderResource;
import com.andeslogistics.platform.u202415551.logistics.interfaces.resources.DeliveryOrderResource;
import com.andeslogistics.platform.u202415551.shared.domain.result.Result;
import com.andeslogistics.platform.u202415551.shared.interfaces.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delivery-orders")
@Tag(name = "Delivery Orders", description = "Delivery order endpoints")
public class DeliveryOrderController {

    private final DeliveryOrderCommandService commandService;
    private final DeliveryOrderQueryService queryService;
    private final MessageSource messageSource;

    public DeliveryOrderController(DeliveryOrderCommandService commandService,
                                   DeliveryOrderQueryService queryService,
                                   MessageSource messageSource) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.messageSource = messageSource;
    }

    @PostMapping
    @Operation(summary = "Create a delivery order")
    public ResponseEntity<?> createDeliveryOrder(@Valid @RequestBody CreateDeliveryOrderResource resource) {
        var command = DeliveryOrderResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command);
        return switch (result) {
            case Result.Success<DeliveryOrder> success -> ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(DeliveryOrderResourceAssembler.toResourceFromEntity(success.value()));
            case Result.Failure<DeliveryOrder> failure -> {
                var status = switch (failure.type()) {
                    case INVALID -> HttpStatus.BAD_REQUEST;
                    case DUPLICATE -> HttpStatus.CONFLICT;
                    case UNEXPECTED -> HttpStatus.INTERNAL_SERVER_ERROR;
                };
                var message = messageSource.getMessage(
                        failure.message(), null, failure.message(), LocaleContextHolder.getLocale());
                yield ResponseEntity.status(status).body(new MessageResource(message));
            }
        };
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a delivery order by id")
    public ResponseEntity<DeliveryOrderResource> getDeliveryOrderById(@PathVariable Long id) {
        var deliveryOrder = queryService.handle(new GetDeliveryOrderByIdQuery(id));
        return deliveryOrder
                .map(order -> ResponseEntity.ok(DeliveryOrderResourceAssembler.toResourceFromEntity(order)))
                .orElse(ResponseEntity.notFound().build());
    }
}
