### Assignment: Battery Charge Monitoring System

**Overview:**
In this assignment, you will develop a simple Battery Charge Monitoring System using Spring Boot, Thymeleaf, and PostgreSQL. The system will allow users to input and monitor the charge levels of various batteries, providing alerts when batteries are running low.

**Objectives:**
- Create a web application using Spring Boot.
- Use Thymeleaf for the frontend.
- Store battery data in a PostgreSQL database.
- Implement functionality to add, view, and monitor battery charge levels.

### Step 1: Model Creation

Create a model named `Battery` with the following attributes:
- `id`: Primary key (Long)
- `name`: Name of the battery (String)
- `chargePercentage`: Current charge percentage (Integer)

```java
package com.example.batterymonitor.model;

import javax.persistence.*;

@Entity
@Table(name = "batteries")
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer chargePercentage;

    // Constructors, getters, and setters
    public Battery() {
    }

    public Battery(String name, Integer chargePercentage) {
        this.name = name;
        this.chargePercentage = chargePercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChargePercentage() {
        return chargePercentage;
    }

    public void setChargePercentage(Integer chargePercentage) {
        this.chargePercentage = chargePercentage;
    }
}
```

### Step 2: Repository

Create a repository interface for the `Battery` model.

```java
package com.example.batterymonitor.repository;

import com.example.batterymonitor.model.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {
}
```

### Step 3: Service Layer

Implement a service class to handle the logic of adding and retrieving batteries.

```java
package com.example.batterymonitor.service;

import com.example.batterymonitor.model.Battery;
import com.example.batterymonitor.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatteryService {
    @Autowired
    private BatteryRepository repository;

    public Battery saveBattery(Battery battery) {
        return repository.save(battery);
    }

    public List<Battery> getAllBatteries() {
        return repository.findAll();
    }
}
```

### Step 4: Controller Layer

Create a controller to handle web requests for adding and viewing batteries.

```java
package com.example.batterymonitor.controller;

import com.example.batterymonitor.model.Battery;
import com.example.batterymonitor.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BatteryController {
    @Autowired
    private BatteryService service;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("batteries", service.getAllBatteries());
        return "home";
    }

    @PostMapping("/add")
    public String addBattery(@ModelAttribute Battery battery) {
        service.saveBattery(battery);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("battery", new Battery());
        return "add";
    }
}
```

### Step 5: Thymeleaf Templates

Create Thymeleaf templates for the home page and the battery addition form.

**home.html**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Battery Monitor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Battery Charge Levels</h1>
    <div class="list-group">
        <div th:each="battery : ${batteries}" class="list-group-item d-flex justify-content-between align-items-center">
            <div th:text="|${battery.name}: ${battery.chargePercentage}%|"></div>
            <span class="badge bg-primary rounded-pill" th:text="${battery.chargePercentage}"></span>
        </div>
    </div>
    <a href="/add" class="btn btn-primary mt-3">Add Battery</a>
</div>
</body>
</html>
```

**add.html**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Add Battery</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Add New Battery</h1>
    <form th:action="@{/add}" th:object="${battery}" method="post" class="mt-3">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" th:field="*{name}" placeholder="Name" required>
        </div>
        <div class="mb-3">
            <label for="chargePercentage" class="form-label">Charge Percentage</label>
            <input type="number" class="form-control" id="chargePercentage" th:field="*{chargePercentage}" placeholder="Charge Percentage" required>
        </div>
        <button type="submit" class="btn btn-success">Add</button>
    </form>
</div>
</body>
</html>
```

This assignment provides a simple yet functional system for monitoring battery charge levels, using Spring Boot, Thymeleaf, and PostgreSQL. It includes basic CRUD operations and can be extended with features like alerts for low battery levels or a more sophisticated user interface.
