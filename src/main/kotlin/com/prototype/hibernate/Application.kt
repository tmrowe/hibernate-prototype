package com.prototype.hibernate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.boot.SpringApplication

@SpringBootApplication
@EnableJpaRepositories(value = ["com.prototype.hibernate.repository"])
class Application {

    companion object Main {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }

}
