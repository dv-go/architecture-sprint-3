package com.device_management.exception;

import java.util.UUID;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(UUID deviceId) {
        super("Устройство с ID " + deviceId + " не найдено.");
    }
}
