package com.example.battery_monitor.controller;


import com.example.battery_monitor.model.Battery;
import com.example.battery_monitor.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BatteryController {
    @Autowired
    private BatteryService batteryService;

    @GetMapping ("/")
    public String home(Model model) {
        model.addAttribute("batteries" , batteryService.getAllBatteries());
        return "home";
    }

    @PostMapping ("/add")
    public String addBattery (@ModelAttribute Battery battery) {
        batteryService.saveBattery(battery);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String showAddForm (Model model){
        model.addAttribute("battery" , new Battery());
        return "add";

    }


}
