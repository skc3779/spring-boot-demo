package boot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

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
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return filter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
