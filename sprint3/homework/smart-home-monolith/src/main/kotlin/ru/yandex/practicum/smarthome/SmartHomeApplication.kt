package ru.yandex.practicum.smarthome

import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan
class SmartHomeApplication

fun main(args: Array<String>) {
    SpringApplication.run(SmartHomeApplication::class.java, *args)
}
