package ru.yandex.practicum.smarthome.controller;

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import ru.yandex.practicum.smarthome.dto.HeatingSystemDto
import ru.yandex.practicum.smarthome.entity.HeatingSystem
import ru.yandex.practicum.smarthome.repository.HeatingSystemRepository


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
internal class HeatingSystemControllerTest {
    @Autowired
    private val mockMvc: MockMvc? = null

    @Autowired
    private val objectMapper: ObjectMapper? = null

    @Autowired
    private val heatingSystemRepository: HeatingSystemRepository? = null

    @Test
    @Throws(Exception::class)
    fun testGetHeatingSystem() {
        var heatingSystem = HeatingSystem()
        heatingSystem.isOn = false
        heatingSystem.targetTemperature = 20.0
        heatingSystem = heatingSystemRepository!!.save(heatingSystem)

        mockMvc!!.perform(MockMvcRequestBuilders.get("/api/heating/{id}", heatingSystem.id))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(heatingSystem.id))
            .andExpect(MockMvcResultMatchers.jsonPath("$.on").value(false))
            .andExpect(MockMvcResultMatchers.jsonPath("$.targetTemperature").value(20.0))
    }

    @Test
    @Throws(Exception::class)
    fun testUpdateHeatingSystem() {
        var heatingSystem = HeatingSystem()
        heatingSystem.isOn = false
        heatingSystem.targetTemperature = 20.0
        heatingSystem = heatingSystemRepository!!.save(heatingSystem)

        val updateDto = HeatingSystemDto(
            heatingSystem.id!!, isOn = true, targetTemperature = 22.5, currentTemperature = 20.0
        )

        mockMvc!!.perform(
            MockMvcRequestBuilders.put("/api/heating/{id}", heatingSystem.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper!!.writeValueAsString(updateDto))
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(heatingSystem.id))
            .andExpect(MockMvcResultMatchers.jsonPath("$.on").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.targetTemperature").value(22.5))
    }

    @Test
    @Throws(Exception::class)
    fun testTurnOn() {
        var heatingSystem = HeatingSystem()
        heatingSystem.isOn = false
        heatingSystem.targetTemperature = 20.0
        heatingSystem = heatingSystemRepository!!.save(heatingSystem)

        mockMvc!!.perform(MockMvcRequestBuilders.post("/api/heating/{id}/turn-on", heatingSystem.id))
            .andExpect(MockMvcResultMatchers.status().isNoContent())

        val updatedHeatingSystem = heatingSystemRepository.findById(heatingSystem.id!!)
            .orElseThrow {
                RuntimeException(
                    "HeatingSystem not found"
                )
            }
        Assertions.assertTrue(updatedHeatingSystem.isOn)
    }

    @Test
    @Throws(Exception::class)
    fun testTurnOff() {
        var heatingSystem = HeatingSystem()
        heatingSystem.isOn = true
        heatingSystem.targetTemperature = 20.0
        heatingSystem = heatingSystemRepository!!.save(heatingSystem)

        mockMvc!!.perform(MockMvcRequestBuilders.post("/api/heating/{id}/turn-off", heatingSystem.id))
            .andExpect(MockMvcResultMatchers.status().isNoContent())

        val updatedHeatingSystem = heatingSystemRepository.findById(heatingSystem.id!!)
            .orElseThrow {
                RuntimeException(
                    "HeatingSystem not found"
                )
            }
        Assertions.assertFalse(updatedHeatingSystem.isOn)
    }

    @Test
    @Throws(Exception::class)
    fun testSetTargetTemperature() {
        var heatingSystem = HeatingSystem()
        heatingSystem.isOn = true
        heatingSystem.targetTemperature = 20.0
        heatingSystem = heatingSystemRepository!!.save(heatingSystem)

        mockMvc!!.perform(
            MockMvcRequestBuilders.post("/api/heating/{id}/set-temperature", heatingSystem.id)
                .param("temperature", "23.5")
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent())

        val updatedHeatingSystem = heatingSystemRepository.findById(heatingSystem.id!!)
            .orElseThrow {
                RuntimeException(
                    "HeatingSystem not found"
                )
            }
        Assertions.assertEquals(23.5, updatedHeatingSystem.targetTemperature, 0.01)
    }

    @Test
    @Throws(Exception::class)
    fun testGetCurrentTemperature() {
        var heatingSystem = HeatingSystem()
        heatingSystem.isOn = true
        heatingSystem.targetTemperature = 20.0
        heatingSystem.currentTemperature = 19.5
        heatingSystem = heatingSystemRepository!!.save(heatingSystem)

        mockMvc!!.perform(MockMvcRequestBuilders.get("/api/heating/{id}/current-temperature", heatingSystem.id))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("19.5"))
    }

    companion object {
        @Container
        var postgres: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test")

        @DynamicPropertySource
        fun registerPgProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { postgres.jdbcUrl }
            registry.add("spring.datasource.username") { postgres.username }
            registry.add("spring.datasource.password") { postgres.password }
        }
    }
}