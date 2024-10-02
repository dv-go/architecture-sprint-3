package com.telemetry.baseservices;

import com.telemetry.entity.Telemetry;
import com.telemetry.repository.TelemetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TelemetryService {

    private final TelemetryRepository telemetryRepository;

    @Autowired
    public TelemetryService(TelemetryRepository telemetryRepository) {
        this.telemetryRepository = telemetryRepository;
    }

    /**
     * Получение самой последней записи по deviceId.
     *
     * @param deviceId ID устройства.
     * @return последняя запись телеметрии.
     */
    public Telemetry getLastTelemetry(UUID deviceId) {
        return telemetryRepository.findTopByDeviceIdOrderByRecordTimeDesc(deviceId);
    }

    /**
     * Получение всех записей устройства за указанный промежуток времени.
     *
     * @param deviceId ID устройства.
     * @param startTime начальное время.
     * @param endTime конечное время.
     * @return список записей телеметрии за указанный промежуток.
     */
    public List<Telemetry> getTelemetryByDeviceIdAndTimeRange(UUID deviceId, LocalDateTime startTime, LocalDateTime endTime) {
        return telemetryRepository.findByDeviceIdAndRecordTimeBetween(deviceId, startTime, endTime); // Исправлено
    }

    /**
     * Сохранение новой записи телеметрии.
     *
     * @param telemetry новая запись телеметрии.
     * @return сохранённая запись.
     */
    public Telemetry saveTelemetry(Telemetry telemetry) {
        return telemetryRepository.save(telemetry);
    }
}
