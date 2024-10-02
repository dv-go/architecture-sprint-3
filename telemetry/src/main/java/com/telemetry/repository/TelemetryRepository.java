package com.telemetry.repository;

import com.telemetry.entity.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, UUID> {

    // Метод для получения самой последней записи по времени записи
    Telemetry findTopByDeviceIdOrderByRecordTimeDesc(UUID deviceId);

    // Метод для получения исторических данных за определенный период
    List<Telemetry> findByDeviceIdAndRecordTimeBetween(UUID deviceId, LocalDateTime startTime, LocalDateTime endTime);
}
