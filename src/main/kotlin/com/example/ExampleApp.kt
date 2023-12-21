package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.scheduler.Schedulers
import com.example.infrastructure.BeansInitializer

@SpringBootApplication
class ExampleApp

fun main(args: Array<String>) {
    Schedulers.enableMetrics()

    val initConfig: SpringApplication.() -> Unit = {
        addInitializers(BeansInitializer())
    }

    runApplication<ExampleApp>(args = args, init = initConfig)
}
