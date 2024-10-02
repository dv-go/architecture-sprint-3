package com.device_management.controller;

import com.device_management.entity.Device;
import com.device_management.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceController deviceController;

    private UUID validDeviceTypeId = UUID.fromString("f8b12f60-5b19-4c7b-8f12-5f7c8b5d5b6d"); // Датчик температуры
    private UUID validModuleId = UUID.fromString("1e5f6e6b-1234-4bba-b3e3-dc8f2197b11a"); // Модуль контроля системы отопления

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Device createTestDevice() {
        Device device = new Device();
        device.setDeviceId(UUID.randomUUID());
        device.setName("Test Device");
        device.setDeviceTypeId(validDeviceTypeId); // Используем действительный ID
        device.setModuleId(validModuleId); // Используем действительный ID
        device.setTargetValue(20.0);
        device.setCurrentValue(19.5);
        device.setOn(true);
        return device;
    }

    @Test
    public void testGetDevice() throws Exception {
        Device device = createTestDevice();
        when(deviceRepository.findById(device.getDeviceId())).thenReturn(java.util.Optional.of(device));

        mockMvc.perform(get("/devices/" + device.getDeviceId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(device.getName()))
                .andExpect(jsonPath("$.target_value").value(device.getTargetValue()));
    }

    @Test
    public void testCreateDevice() throws Exception {
        Device device = createTestDevice();
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        mockMvc.perform(post("/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Device\", \"deviceTypeId\": \"" + validDeviceTypeId + "\", \"moduleId\": \"" + validModuleId + "\", \"targetValue\": 20.0, \"currentValue\": 19.5, \"isOn\": true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(device.getName()))
                .andExpect(jsonPath("$.target_value").value(device.getTargetValue()));
    }

    @Test
    public void testUpdateDevice() throws Exception {
        Device device = createTestDevice();
        when(deviceRepository.findById(device.getDeviceId())).thenReturn(java.util.Optional.of(device));

        mockMvc.perform(put("/devices/" + device.getDeviceId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Device\", \"deviceTypeId\": \"" + validDeviceTypeId + "\", \"moduleId\": \"" + validModuleId + "\", \"targetValue\": 25.0, \"currentValue\": 24.0, \"isOn\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Device"))
                .andExpect(jsonPath("$.target_value").value(25.0));
    }

    @Test
    public void testGetAllDevices() throws Exception {
        List<Device> devices = new ArrayList<>();
        devices.add(createTestDevice());
        when(deviceRepository.findAll()).thenReturn(devices);

        mockMvc.perform(get("/devices")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(devices.size()));
    }

    @Test
    public void testDeleteDevice() throws Exception {
        Device device = createTestDevice();
        when(deviceRepository.findById(device.getDeviceId())).thenReturn(java.util.Optional.of(device));
        doNothing().when(deviceRepository).deleteById(device.getDeviceId());

        mockMvc.perform(delete("/devices/" + device.getDeviceId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
