package ru.yandex.practicum.smarthome.service;

import ru.yandex.practicum.smarthome.dto.HeatingSystemDto

interface HeatingSystemService {
    fun getHeatingSystem(id: Long): HeatingSystemDto
    fun updateHeatingSystem(id: Long, heatingSystemDto: HeatingSystemDto): HeatingSystemDto
    fun turnOn(id: Long)
    fun turnOff(id: Long)
    fun setTargetTemperature(id: Long, temperature: Double)
    fun getCurrentTemperature(id: Long): Double?
}
