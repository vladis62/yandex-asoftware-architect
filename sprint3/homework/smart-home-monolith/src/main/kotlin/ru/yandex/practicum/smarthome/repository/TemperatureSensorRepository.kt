package ru.yandex.practicum.smarthome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.smarthome.entity.TemperatureSensor;

@Repository
interface TemperatureSensorRepository: JpaRepository<TemperatureSensor, Long>
