package com.device_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "devices")
@Data
public class Device {
    @Id
    @Column(name = "device_id", nullable = false, updatable = false)
    private UUID deviceId = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "device_type_id", nullable = false)
    private UUID deviceTypeId;

    @Column(name = "module_id", nullable = false)
    private UUID moduleId;

    @Column(name = "target_value", nullable = false)
    private double targetValue;

    @Column(name = "current_value", nullable = false)
    private double currentValue;

    @Column(name = "is_on", nullable = false)
    private boolean isOn;
}
