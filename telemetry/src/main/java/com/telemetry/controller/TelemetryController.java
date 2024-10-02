package com.telemetry.controller;

import com.telemetry.entity.Telemetry;
import com.telemetry.baseservices.TelemetryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devices/{deviceId}/telemetry")
public class TelemetryController {

    private final TelemetryService telemetryService;

    @Autowired
    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    /**
     * Получение последних данных телеметрии.
     *
     * @param deviceId ID устройства.
     * @return последняя запись телеметрии.
     */
    @Operation(
            summary = "Получение последних данных телеметрии",
            description = "Возвращает последнее полученное значение телеметрии для устройства.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Последняя запись телеметрии",
                            content = @Content(schema = @Schema(implementation = Telemetry.class))),
                    @ApiResponse(responseCode = "404", description = "Устройство не найдено", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
            }
    )
    @GetMapping("/latest")
    public ResponseEntity<Telemetry> getLatestTelemetry(
            @Parameter(description = "Уникальный идентификатор устройства", example = "8f14e45f-eeb1-44e3-b2e8-5a61e6dcdc22")
            @PathVariable UUID deviceId) {
        Telemetry telemetry = telemetryService.getLastTelemetry(deviceId);
        return ResponseEntity.ok(telemetry);
    }

    /**
     * Получение исторических данных телеметрии за указанный период.
     *
     * @param deviceId  ID устройства.
     * @param startTime начальное время.
     * @param endTime   конечное время.
     * @return список записей телеметрии за указанный промежуток.
     */
    @Operation(
            summary = "Получение исторических данных телеметрии",
            description = "Возвращает данные телеметрии за указанный период времени.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Исторические данные телеметрии",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Telemetry.class)))),
                    @ApiResponse(responseCode = "404", description = "Устройство не найдено", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
            }
    )
    @GetMapping
    public ResponseEntity<List<Telemetry>> getTelemetryByTimeRange(
            @Parameter(description = "Уникальный идентификатор устройства", example = "8f14e45f-eeb1-44e3-b2e8-5a61e6dcdc22")
            @PathVariable UUID deviceId,
            @Parameter(description = "Начальное время периода", example = "2024-09-30T00:00:00")
            @RequestParam("startTime") LocalDateTime startTime,
            @Parameter(description = "Конечное время периода", example = "2024-09-30T23:59:59")
            @RequestParam("endTime") LocalDateTime endTime) {
        List<Telemetry> telemetryList = telemetryService.getTelemetryByDeviceIdAndTimeRange(deviceId, startTime, endTime);
        return ResponseEntity.ok(telemetryList);
    }

    /**
     * Создание новой записи телеметрии.
     *
     * @param deviceId  ID устройства.
     * @param telemetry данные для сохранения.
     * @return сохранённая запись.
     */
    @Operation(
            summary = "Создание новой записи телеметрии",
            description = "Сохраняет новую запись телеметрии для указанного устройства.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Запись телеметрии успешно сохранена",
                            content = @Content(schema = @Schema(implementation = Telemetry.class))),
                    @ApiResponse(responseCode = "404", description = "Устройство не найдено", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<Telemetry> createTelemetry(
            @Parameter(description = "Уникальный идентификатор устройства", example = "8f14e45f-eeb1-44e3-b2e8-5a61e6dcdc22")
            @PathVariable UUID deviceId,
            @RequestBody Telemetry telemetry) {
        telemetry.setDeviceId(deviceId); // Устанавливаем deviceId в объект telemetry
        Telemetry savedTelemetry = telemetryService.saveTelemetry(telemetry);
        return ResponseEntity.ok(savedTelemetry);
    }
}
