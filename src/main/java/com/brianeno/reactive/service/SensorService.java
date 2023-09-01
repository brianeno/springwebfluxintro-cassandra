package com.brianeno.reactive.service;

import com.brianeno.reactive.model.Sensor;
import com.brianeno.reactive.repository.SensorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SensorService {
    private final SensorRepository repository;

    public SensorService(final SensorRepository repository) {
        this.repository = repository;
    }

    public Flux<Sensor> findAll() {
        List<Sensor> list = this.repository.findAll();
        return Flux.fromIterable(list);
    }

    public Mono<Sensor> findById(String id) {

        List<Sensor> sensor = this.repository.findByKeySensorId(id);
        if (sensor.size() > 0) {
            return Mono.just(sensor.get(0));
        } else {
            return Mono.empty();
        }
    }
}
