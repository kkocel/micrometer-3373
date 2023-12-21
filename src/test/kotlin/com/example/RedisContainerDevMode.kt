package com.example

import org.springframework.boot.devtools.restart.RestartScope
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.GenericContainer

@TestConfiguration
class RedisContainerDevMode {
    @Bean
    @ServiceConnection("redis")
    @RestartScope
    fun redis(): GenericContainer<*> = GenericContainer("redis:latest").withExposedPorts(6379)
}
