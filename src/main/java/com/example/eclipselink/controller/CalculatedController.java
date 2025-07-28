package com.example.eclipselink.controller;

import com.example.eclipselink.model.Calculated;
import com.example.eclipselink.service.CalculatedService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calc")
public class CalculatedController {

    private final CalculatedService service;

    public CalculatedController(CalculatedService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    private List<Calculated> getAll(){
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    private Calculated save(@RequestBody Calculated calculated){
        return service.save(calculated);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/collect")
    private Integer createSaveList(){
        return service.listSaveGenerated();
    }
}
