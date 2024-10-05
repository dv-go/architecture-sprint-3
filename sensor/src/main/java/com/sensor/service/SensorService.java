package com.sensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SensorService {

    private final RestTemplate restTemplate;
    private final String telemetryServiceUrl = "http://telemetry:8082/devices/{deviceId}/telemetry";

    // Фиксированный идентификатор устройства
    private final UUID deviceId = UUID.fromString("8f14e45f-eeb1-44e3-b2e8-5a61e6dcdc22");

    @Autowired
    public SensorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Генерация и отправка данных телеметрии каждую секунду
    @Scheduled(fixedRate = 1000)
    public void sendRandomTelemetryData() {
        String url = telemetryServiceUrl.replace("{deviceId}", deviceId.toString());

        // Генерация данных телеметрии для комнатной температуры
        Map<String, Object> telemetryData = new HashMap<>();
        telemetryData.put("id", UUID.randomUUID());
        telemetryData.put("deviceId", deviceId);
        telemetryData.put("dataType", "temperature");  // Тип данных: температура
        telemetryData.put("dataValue", 20 + Math.random() * 5);  // Случайное значение от 20 до 25
        telemetryData.put("recordTime", LocalDateTime.now());
        telemetryData.put("isOn", true);  // Статус сенсора (включен)

        try {
            // Отправка POST-запроса с данными телеметрии
            restTemplate.postForObject(url, telemetryData, String.class);
            System.out.println("Sent telemetry data: " + telemetryData);
        } catch (Exception e) {
            System.out.println("Failed to send telemetry data: " + e.getMessage());
        }
    }
}
