package com.prototype.hibernate.configuration

import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.RequestHandler
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * This class is responsible for the global configuration of Swagger.
 */
@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun swagger() : Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(Predicates.not<RequestHandler>(RequestHandlerSelectors.basePackage("org.springframework.boot")))
            .paths(PathSelectors.any())
            .build()
    }

}
