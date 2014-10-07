package boot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by kaha on 2014. 10. 8..
 */
@Service
@Profile("pro2")
public class Pro2ServiceImpl implements SomethingService {
    @Override
    public String pirnt() {
        return "pro2";
    }
}
