package com.example.battery_monitor.repository;

import com.example.battery_monitor.model.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends JpaRepository <Battery, Long> {
}
