package cl.falabella.code.challengefreddyparedes.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * APP Config
 * @author Freddy Paredes
 * @version 1.0
 */
@Component
public class AppConfig {

    /**
     * Used for map DTO -> Entity
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}// class closure
