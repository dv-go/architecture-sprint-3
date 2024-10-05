package com.device_management.service;

import com.device_management.dto.DeviceDto;
import com.device_management.dto.DeviceStatusDto;
import com.device_management.dto.DeviceCommandDto;

import java.util.UUID;

public interface DeviceService {
    DeviceDto getDevice(UUID deviceId);
    void updateDeviceStatus(UUID deviceId, DeviceStatusDto statusDto);
    void sendCommand(UUID deviceId, DeviceCommandDto commandDto);
}