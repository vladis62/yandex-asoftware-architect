package ru.yandex.practicum.smarthome.service

import org.springframework.stereotype.Service
import ru.yandex.practicum.smarthome.dto.HeatingSystemDto
import ru.yandex.practicum.smarthome.entity.HeatingSystem
import ru.yandex.practicum.smarthome.repository.HeatingSystemRepository
import ru.yandex.practicum.smarthome.service.HeatingSystemService

@Service
class HeatingSystemServiceImpl(
    private val heatingSystemRepository: HeatingSystemRepository
) : HeatingSystemService {


    override fun getHeatingSystem(id: Long): HeatingSystemDto {
        val heatingSystem = heatingSystemRepository.findById(id)
            .orElseThrow {
                RuntimeException(
                    "HeatingSystem not found"
                )
            }
        return convertToDto(heatingSystem)
    }

    override fun updateHeatingSystem(id: Long, heatingSystemDto: HeatingSystemDto): HeatingSystemDto {
        val existingHeatingSystem = heatingSystemRepository.findById(id)
            .orElseThrow {
                RuntimeException(
                    "HeatingSystem not found"
                )
            }
        existingHeatingSystem.isOn = heatingSystemDto.isOn
        existingHeatingSystem.targetTemperature = heatingSystemDto.targetTemperature
        val updatedHeatingSystem = heatingSystemRepository.save(existingHeatingSystem)
        return convertToDto(updatedHeatingSystem)
    }

    override fun turnOn(id: Long) {
        val heatingSystem = heatingSystemRepository.findById(id)
            .orElseThrow {
                RuntimeException(
                    "HeatingSystem not found"
                )
            }
        heatingSystem.isOn = true
        heatingSystemRepository.save(heatingSystem)
    }

    override fun turnOff(id: Long) {
        val heatingSystem = heatingSystemRepository.findById(id)
            .orElseThrow {
                RuntimeException(
                    "HeatingSystem not found"
                )
            }
        heatingSystem.isOn = false
        heatingSystemRepository.save(heatingSystem)
    }

    override fun setTargetTemperature(id: Long, temperature: Double) {
        val heatingSystem = heatingSystemRepository.findById(id)
            .orElseThrow {
                RuntimeException(
                    "HeatingSystem not found"
                )
            }
        heatingSystem.targetTemperature = temperature
        heatingSystemRepository.save(heatingSystem)
    }

    override fun getCurrentTemperature(id: Long): Double {
        val heatingSystem = heatingSystemRepository.findById(id)
            .orElseThrow {
                RuntimeException(
                    "HeatingSystem not found"
                )
            }
        return heatingSystem.currentTemperature
    }

    private fun convertToDto(heatingSystem: HeatingSystem): HeatingSystemDto {
        return HeatingSystemDto(
            id = heatingSystem.id!!,
            isOn = heatingSystem.isOn,
            targetTemperature = heatingSystem.targetTemperature,
            currentTemperature = heatingSystem.currentTemperature,
        )
    }
}