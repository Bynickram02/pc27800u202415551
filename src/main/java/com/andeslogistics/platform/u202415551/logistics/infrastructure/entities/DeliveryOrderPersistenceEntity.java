package com.andeslogistics.platform.u202415551.logistics.infrastructure.entities;

import com.andeslogistics.platform.u202415551.logistics.domain.model.valueobjects.DeliveryStatus;
import com.andeslogistics.platform.u202415551.shared.infrastructure.persistence.jpa.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "delivery_orders")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryOrderPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "delivery_code", nullable = false, unique = true)
    private String deliveryCode;

    @Column(name = "warehouse_id", nullable = false, length = 36)
    private String warehouseId;

    @Column(name = "delivery_window_start_date", nullable = false)
    private LocalDate deliveryWindowStartDate;

    @Column(name = "delivery_window_end_date", nullable = false)
    private LocalDate deliveryWindowEndDate;

    @Column(name = "package_load_quantity", nullable = false)
    private Integer packageLoadQuantity;

    @Column(name = "package_load_unit", nullable = false)
    private String packageLoadUnit;

    @Column(name = "priority_level", nullable = false)
    private Integer priorityLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DeliveryStatus status;

    @Column(name = "delivery_notes")
    private String deliveryNotes;
}
