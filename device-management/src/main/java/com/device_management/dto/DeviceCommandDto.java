package com.device_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeviceCommandDto {
    @Schema(description = "Команда для устройства", example = "SET_TEMPERATURE")
    private DeviceCommand command;

    @Schema(description = "Значение для команды (если необходимо)", example = "20.0")
    private double value;
}
