package boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by kaha on 2014. 10. 7..
 */
@SpringBootApplication
public class SpringBootDemoApplication {
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return filter;
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
