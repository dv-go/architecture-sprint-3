package com.device_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Команды для устройства")
public enum DeviceCommand {
    @Schema(description = "Установить температуру", example = "setTemperature")
    SET_TEMPERATURE("setTemperature"),

    @Schema(description = "Включить устройство", example = "turnOn")
    TURN_ON("turnOn"),

    @Schema(description = "Выключить устройство", example = "turnOff")
    TURN_OFF("turnOff");

    private final String command;

    DeviceCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
