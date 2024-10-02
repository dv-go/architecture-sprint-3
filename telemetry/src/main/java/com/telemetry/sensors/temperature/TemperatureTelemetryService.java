package com.telemetry.sensors.temperature;

import com.telemetry.repository.TelemetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperatureTelemetryService {
    private final TelemetryRepository temperatureTelemetryRepository;

    @Autowired
    public TemperatureTelemetryService(TelemetryRepository temperatureTelemetryRepository) {
        this.temperatureTelemetryRepository = temperatureTelemetryRepository;
    }

}
