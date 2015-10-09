package org.ametiste.utils.signals;

/**
 * Created by atlantis on 5/27/15.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(SignalsConfiguration.class)
public class SignalsBoot {
    public static void main(String[] args) {

        SpringApplication.run(SignalsBoot.class, args);

    }
}
