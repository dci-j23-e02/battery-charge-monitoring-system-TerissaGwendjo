package com.example.battery_monitor.service;

import com.example.battery_monitor.model.Battery;
import com.example.battery_monitor.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatteryService {
    @Autowired
    private BatteryRepository batteryRepository;

    public Battery saveBattery(Battery battery) {
        return batteryRepository.save(battery);
    }

    public List <Battery> getAllBatteries() {
        return batteryRepository.findAll();
    }


}
