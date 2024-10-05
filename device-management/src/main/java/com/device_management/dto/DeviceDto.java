package com.device_management.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class DeviceDto {
    private UUID deviceId;
    private String name;
    private UUID deviceTypeId;
    private UUID moduleId;
    private double targetValue;
    private double currentValue;
    private boolean isOn;
}