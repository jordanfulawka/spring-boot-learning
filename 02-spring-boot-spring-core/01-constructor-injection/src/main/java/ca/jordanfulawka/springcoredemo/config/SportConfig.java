package ca.jordanfulawka.springcoredemo.config;

import ca.jordanfulawka.springcoredemo.common.Coach;
import ca.jordanfulawka.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
