package org.example.racekatteklubben;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

// DataSourceAutoConfiguration er ekskluderet indtil database er sat op
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RaceKatteKlubbenApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaceKatteKlubbenApplication.class, args);
    }

}
