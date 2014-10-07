package springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kaha on 2014. 10. 7..
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping
    @ResponseBody
    public String index() {
        return "Hello Spring Boot?";
    }
}
