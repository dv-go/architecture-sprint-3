package com.device_management.service;

import com.device_management.dto.*;
import com.device_management.entity.Device;
import com.device_management.exception.DeviceNotFoundException;
import com.device_management.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.device_management.dto.Telemetry;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final UUID DEVICE_ID = UUID.fromString("8f14e45f-eeb1-44e3-b2e8-5a61e6dcdc22");

    @Scheduled(fixedRate = 5000)
    public void fetchAndUpdateCurrentValue() {
        try {
            Telemetry telemetry = restTemplate.getForObject(
                    "http://telemetry:8082/devices/" + DEVICE_ID + "/telemetry/latest",
                    Telemetry.class
            );

            if (telemetry != null) {
                updateCurrentValue(telemetry);
            }
        } catch (Exception e) {
            System.err.println("Error fetching telemetry: " + e.getMessage());
        }
    }

    private void updateCurrentValue(Telemetry telemetry) {
        Device device = deviceRepository.findById(telemetry.getDeviceId())
                .orElseThrow(() -> new DeviceNotFoundException(telemetry.getDeviceId()));

        device.setCurrentValue(telemetry.getDataValue());
        deviceRepository.save(device);
    }

    @Override
    public DeviceDto getDevice(UUID deviceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
        return convertToDto(device);
    }

    private DeviceDto convertToDto(Device device) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDeviceId(device.getDeviceId());
        deviceDto.setName(device.getName());
        deviceDto.setDeviceTypeId(device.getDeviceTypeId());
        deviceDto.setModuleId(device.getModuleId());
        deviceDto.setTargetValue(device.getTargetValue());
        deviceDto.setCurrentValue(device.getCurrentValue());
        deviceDto.setOn(device.isOn());
        return deviceDto;
    }

    @Override
    public void updateDeviceStatus(UUID deviceId, DeviceStatusDto statusDto) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
        device.setOn(statusDto.isOn());
        deviceRepository.save(device);
    }

    @Override
    public void sendCommand(UUID deviceId, DeviceCommandDto commandDto) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));

        DeviceCommand command = commandDto.getCommand();

        switch (command) {
            case SET_TEMPERATURE:
                device.setTargetValue(commandDto.getValue());
                break;
            case TURN_ON:
                device.setOn(true);
                break;
            case TURN_OFF:
                device.setOn(false);
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + command);
        }

        deviceRepository.save(device);
    }
}
