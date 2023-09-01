package com.brianeno.reactive.repository;

import com.brianeno.reactive.model.Sensor;
import com.brianeno.reactive.model.SensorKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorRepository extends CassandraRepository<Sensor, SensorKey> {

    List<Sensor> findByKeySensorId(final String sensorId);

    List<Sensor> findByKeySensorIdAndKeyReportedTimeGreaterThan(
            final String sensorId, final LocalDateTime reportedTime);
}
