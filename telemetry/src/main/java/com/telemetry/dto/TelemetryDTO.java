package com.telemetry.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TelemetryDTO {
    private UUID id;
    private UUID deviceId;
    private String dataType;
    private double dataValue;
    private LocalDateTime recordTime;
    private boolean isOn;

    // Конструктор с параметрами
    public TelemetryDTO(UUID id, UUID deviceId, String dataType, double dataValue, LocalDateTime recordTime, boolean isOn) {
        this.id = id;
        this.deviceId = deviceId;
        this.dataType = dataType;
        this.dataValue = dataValue;
        this.recordTime = recordTime;
        this.isOn = isOn;
    }

    // Конструктор по умолчанию
    public TelemetryDTO() {
    }
}
