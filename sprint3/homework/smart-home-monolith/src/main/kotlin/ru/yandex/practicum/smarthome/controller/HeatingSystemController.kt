package ru.yandex.practicum.smarthome.controller;

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.yandex.practicum.smarthome.dto.HeatingSystemDto
import ru.yandex.practicum.smarthome.service.HeatingSystemService


@RestController
@RequestMapping("/api/heating")
class HeatingSystemController(
    private val heatingSystemService: HeatingSystemService
) {

    @GetMapping("/{id}")
    fun getHeatingSystem(@PathVariable("id") id: Long): ResponseEntity<HeatingSystemDto> {
        logger.info("Fetching heating system with id {}", id)
        return ResponseEntity.ok(heatingSystemService.getHeatingSystem(id))
    }

    @PutMapping("/{id}")
    fun updateHeatingSystem(
        @PathVariable("id") id: Long,
        @RequestBody heatingSystemDto: HeatingSystemDto
    ): ResponseEntity<HeatingSystemDto> {
        logger.info("Updating heating system with id {}", id)
        return ResponseEntity.ok(heatingSystemService.updateHeatingSystem(id, heatingSystemDto))
    }

    @PostMapping("/{id}/turn-on")
    fun turnOn(@PathVariable("id") id: Long): ResponseEntity<Void> {
        logger.info("Turning on heating system with id {}", id)
        heatingSystemService.turnOn(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{id}/turn-off")
    fun turnOff(@PathVariable("id") id: Long): ResponseEntity<Void> {
        logger.info("Turning off heating system with id {}", id)
        heatingSystemService.turnOff(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{id}/set-temperature")
    fun setTargetTemperature(@PathVariable("id") id: Long, @RequestParam temperature: Double): ResponseEntity<Void> {
        logger.info("Setting target temperature to {} for heating system with id {}", temperature, id)
        heatingSystemService.setTargetTemperature(id, temperature)
        // TODO: Implement automatic temperature maintenance logic in the service layer
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}/current-temperature")
    fun getCurrentTemperature(@PathVariable("id") id: Long): ResponseEntity<Double> {
        logger.info("Fetching current temperature for heating system with id {}", id)
        return ResponseEntity.ok(heatingSystemService.getCurrentTemperature(id))
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HeatingSystemController::class.java)
    }
}
