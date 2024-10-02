package com.device_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Telemetry {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("deviceId")
    private UUID deviceId;

    @JsonProperty("dataType")
    private String dataType;

    @JsonProperty("dataValue")
    private Double dataValue;

    @JsonProperty("recordTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime recordTime;

    @JsonProperty("isOn")
    private Boolean isOn;
}
