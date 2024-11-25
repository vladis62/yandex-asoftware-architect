package ru.yandex.practicum.smarthome.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "heating_systems")
class HeatingSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    var isOn = false

    @Column(nullable = false)
    var targetTemperature = 0.0

    @Column(nullable = false)
    var currentTemperature = 0.0
}