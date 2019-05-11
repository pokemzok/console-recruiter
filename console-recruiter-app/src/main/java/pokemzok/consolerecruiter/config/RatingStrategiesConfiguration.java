package pokemzok.consolerecruiter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pokemzok.consolerecruiter.domain.CountingPointsStrategy;
import pokemzok.consolerecruiter.recruit.LenientCountingStrategy;
import pokemzok.consolerecruiter.recruit.UniversityCountingStrategy;

@Configuration
public class RatingStrategiesConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "lenient-strategy", name="enabled")
    public CountingPointsStrategy lenientCountingStrategy(){
        return new LenientCountingStrategy();
    }

    @Bean
    @ConditionalOnProperty(prefix = "university-strategy", name="enabled")
    public CountingPointsStrategy universityCountingStrategy(){
        return new UniversityCountingStrategy();
    }
}
