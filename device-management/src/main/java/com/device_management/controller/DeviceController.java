package com.device_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.device_management.dto.DeviceDto;
import com.device_management.service.DeviceService;
import com.device_management.dto.DeviceStatusDto; // Импортируйте новый DTO
import com.device_management.dto.DeviceCommandDto; // Импортируйте новый DTO

import java.util.UUID;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @GetMapping("/{device_id}")
    @Operation(summary = "Получить информацию об устройстве",
            description = "Возвращает информацию об устройстве по его уникальному идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация об устройстве успешно получена."),
                    @ApiResponse(responseCode = "404", description = "Устройство с данным идентификатором не найдено.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера.", content = @Content())
            })
    public ResponseEntity<DeviceDto> getDevice(
            @Parameter(description = "Уникальный идентификатор устройства", example = "8f14e45f-eeb1-44e3-b2e8-5a61e6dcdc22")
            @PathVariable("device_id") @Schema(description = "Уникальный идентификатор устройства", example = "8f14e45f-eeb1-44e3-b2e8-5a61e6dcdc22") UUID deviceId) {
        logger.info("Fetching device with device_id {}", deviceId);
        return ResponseEntity.ok(deviceService.getDevice(deviceId));
    }


    @PutMapping("/{device_id}/status")
    @Operation(summary = "Обновить статус устройства",
            description = "Обновляет статус устройства по его уникальному идентификатору.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Статус устройства успешно обновлен."),
                    @ApiResponse(responseCode = "400", description = "Неверный запрос, например, если указанный идентификатор устройства не существует."),
                    @ApiResponse(responseCode = "404", description = "Устройство с данным идентификатором не найдено."),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера.")
            })
    public ResponseEntity<Void> updateDeviceStatus(
            @Parameter(description = "Уникальный идентификатор устройства", example = "8f14e45f-eeb1-44e3-b2e8-5a61e6dcdc22")
            @PathVariable("device_id") UUID deviceId,

            @RequestBody DeviceStatusDto statusDto) {
        logger.info("Updating status for device with device_id {}", deviceId);
        deviceService.updateDeviceStatus(deviceId, statusDto);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{device_id}/commands")
    @Operation(summary = "Отправить команду устройству",
            description = "Отправляет указанную команду устройству по его уникальному идентификатору. Возможные команды: SET_TEMPERATURE, TURN_ON, TURN_OFF.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Команда успешно отправлена устройству."),
                    @ApiResponse(responseCode = "400", description = "Неверный запрос, например, если команда не распознана или устройство не найдено."),
                    @ApiResponse(responseCode = "404", description = "Устройство с данным идентификатором не найдено."),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера.")
            })
    public ResponseEntity<Void> sendCommand(
            @Parameter(description = "Уникальный идентификатор устройства", example = "8f14e45f-eeb1-44e3-b2e8-5a61e6dcdc22")
            @PathVariable("device_id") UUID deviceId,

            @Parameter(description = "Команда для выполнения на устройстве. Возможные значения: SET_TEMPERATURE, TURN_ON, TURN_OFF.")
            @RequestBody DeviceCommandDto commandDto) {
        logger.info("Sending command to device with device_id {}", deviceId);
        deviceService.sendCommand(deviceId, commandDto);
        return ResponseEntity.noContent().build();
    }

}
