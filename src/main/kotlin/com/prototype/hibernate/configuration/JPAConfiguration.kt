package com.prototype.hibernate.configuration

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder

@Configuration
@EnableTransactionManagement
class JPAConfiguration {

    @Bean
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        dataSource: DataSource,
        @Value("\${spring.jpa.hibernate.naming.physical-strategy}") physicalNamingStrategy : String,
        @Value("\${spring.jpa.hibernate.naming.implicit-strategy}") implicitNamingStrategy : String
    ): LocalContainerEntityManagerFactoryBean {
        val modelPackage = "com.prototype.hibernate.model.entity"
        val properties = mapOf<String, Any>(
            "hibernate.physical_naming_strategy" to physicalNamingStrategy,
            "hibernate.implicit_naming_strategy" to implicitNamingStrategy
        )

        return builder
            .dataSource(dataSource)
            .packages(modelPackage)
            .properties(properties)
            .build()
    }

    @Bean
    fun dataSource(
        @Value("\${spring.datasource.driverClassName}") driverClassName : String,
        @Value("\${spring.datasource.url}") url : String,
        @Value("\${spring.datasource.username}") username : String,
        @Value("\${spring.datasource.password}") password : String
    ) : DataSource {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = driverClassName
        dataSource.url = url
        dataSource.username = username
        dataSource.password = password
        return dataSource
    }

}
