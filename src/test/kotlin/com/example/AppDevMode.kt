package com.example

import org.springframework.boot.fromApplication

object AppDevMode {
    @JvmStatic
    fun main(args: Array<String>) {
        fromApplication<ExampleApp>()
            .with(RedisContainerDevMode::class.java)
            .run(*args)
    }
}
