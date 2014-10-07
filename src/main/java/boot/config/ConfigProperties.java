package boot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * application.properties의 config
 */
@Component
@ConfigurationProperties(prefix="config")
@Getter
@Setter
public class ConfigProperties {
    private String name;
    private String text;
}
