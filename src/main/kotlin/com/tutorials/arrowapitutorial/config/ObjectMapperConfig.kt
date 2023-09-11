package com.tutorials.arrowapitutorial.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.ResolverStyle


@Configuration
class ObjectMapperConfig {

    @Bean(name = ["objectMapper"])
    fun getObjectMapper(): ObjectMapper {
        return JsonMapper.builder()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build().registerModule(
                JavaTimeModule()
                    .addDeserializer(
                        LocalDateTime::class.java, LocalDateTimeDeserializer(
                            DateTimeFormatter.ISO_DATE.withResolverStyle(
                                ResolverStyle.SMART
                            )
                        )
                    )
            ).registerModule(ParameterNamesModule())
    }
}