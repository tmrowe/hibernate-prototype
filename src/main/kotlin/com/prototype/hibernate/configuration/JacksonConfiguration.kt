package com.prototype.hibernate.configuration

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * This class is responsible for the global configuration of the Jackson ObjectMapper.
 */
@Configuration
class JacksonConfiguration {

    @Bean
    fun datatypeHibernateModule() : Hibernate5Module {
        return Hibernate5Module()
    }

}
