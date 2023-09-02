package com.brianeno.reactive.controller;

import com.brianeno.reactive.model.Sensor;
import com.brianeno.reactive.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/readings")
public class SensorReadingController {

    private final SensorService service;

    @GetMapping
    public Flux<Sensor> getAllSensors() {
        return this.service.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Sensor>> getSensorId(@PathVariable String id) {
        Mono<Sensor> readings = this.service.findById(id);
        return readings.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("{id}/reported/{reportedTime}")
    public Flux<Sensor> getSensorId(@PathVariable String id,
                                    @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
                                    LocalDateTime reportedTime) {
        Flux<Sensor> readings = this.service.findByIdAndReportedTime(id, reportedTime);
        return readings;
    }
}

