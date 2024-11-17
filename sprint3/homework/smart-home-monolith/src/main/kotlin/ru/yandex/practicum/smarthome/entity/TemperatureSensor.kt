package ru.yandex.practicum.smarthome.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "temperature_sensors")
class TemperatureSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false)
    private var currentTemperature = 0.0

    @Column(nullable = false)
    private var lastUpdated: LocalDateTime? = null
}

