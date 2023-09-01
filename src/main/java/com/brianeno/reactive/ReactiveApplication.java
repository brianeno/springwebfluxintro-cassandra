package com.brianeno.reactive;

import com.brianeno.reactive.model.Sensor;
import com.brianeno.reactive.model.SensorKey;
import com.brianeno.reactive.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class ReactiveApplication implements CommandLineRunner {

    @Autowired
    private SensorRepository sensorRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApplication.class, args);
    }

    @Override
    public void run(String... args) {
        SensorKey key = new SensorKey("1234", LocalDateTime.now().minusSeconds(10));
        Sensor s = new Sensor(key, "121", LocalDateTime.now());
        sensorRepository.insert(s);

        key = new SensorKey("1234", LocalDateTime.now());
        s = new Sensor(key, "122.5", LocalDateTime.now());
        sensorRepository.insert(s);

        System.out.println("find by sensor_id");
        sensorRepository.findByKeySensorId("1234").forEach(System.out::println);

        System.out.println("find all");
        sensorRepository.findByKeySensorIdAndKeyReportedTimeGreaterThan("1234", LocalDateTime.now().minusDays(2)).forEach(System.out::println);

        System.out.println("find all");
        sensorRepository.findAll().forEach(System.out::println);
    }
}
