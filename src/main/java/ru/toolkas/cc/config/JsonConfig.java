package ru.toolkas.cc.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JsonConfig {
  public static final DateTimeFormatter CC_LOCALDATE_FORMATTER = DateTimeFormatter
          .ofPattern("dd-MM-yyyy");

  @Bean
  public Module javaTimeModule() {
    JavaTimeModule module = new JavaTimeModule();
    module.addSerializer(new LocalDateSerializer(CC_LOCALDATE_FORMATTER));
    module.addDeserializer(LocalDate.class, new LocalDateDeserializer(CC_LOCALDATE_FORMATTER));
    return module;
  }
}
