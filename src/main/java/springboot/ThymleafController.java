package springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kaha on 2014. 10. 7..
 */
@Controller
public class ThymleafController {

    @RequestMapping("/thymeleaf")
    @ResponseBody
    public ModelAndView index()
    {
        return new ModelAndView("thymeleaf");

    }
}
