package boot.profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by seocomp-pc on 2016-07-21.
 */
@Configuration
@Profile("dev")
@Slf4j
public class CacheConfigDev {
    @Bean
    public CacheManager concurrentMapCacheManager() {
        log.debug("Cache manager is concurrentMapCacheManager");
        return new ConcurrentMapCacheManager("movieFindCache");
    }
}
