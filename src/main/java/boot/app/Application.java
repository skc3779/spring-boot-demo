package boot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kaha on 2014. 10. 7..
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({"boot.config",
        "boot.entity",
        "boot.repository",
        "boot.service",
        "boot.controller"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
