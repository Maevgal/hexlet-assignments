package exercise;

import exercise.daytime.Day;
import exercise.daytime.Daytime;
import exercise.daytime.Night;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalTime;

// BEGIN


// END
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @RequestScope
    @Bean
    public static Daytime getDaytime() {
        LocalTime time = LocalTime.now();
        return time.isAfter(LocalTime.of(6, 0, 1))
                && time.isBefore(LocalTime.of(22, 0)) ? new Day() : new Night();
    }

    // END
}
