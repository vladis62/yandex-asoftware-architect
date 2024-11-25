package ru.yandex.practicum.smarthome.dto

data class HeatingSystemDto(
    val id: Long,
    val isOn: Boolean,
    val targetTemperature: Double,
    val currentTemperature: Double
)
