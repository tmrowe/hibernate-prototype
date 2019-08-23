package com.prototype.hibernate.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer

/**
 * Configuration for Spring Web MCV:
 * - Disables the suffix pattern matching. Enabling us to include '.' in our paths by default. E.g. Email addresses.
 */
@Configuration
class WebMVCConfiguration : WebMvcConfigurationSupport() {

    override fun requestMappingHandlerMapping() : RequestMappingHandlerMapping {
        val handlerMapping = super.requestMappingHandlerMapping()
        handlerMapping.setUseSuffixPatternMatch(false)
        return handlerMapping
    }

    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        configurer.favorPathExtension(false)
    }

}
