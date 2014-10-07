package boot.controller;

import boot.config.ConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaha on 2014. 10. 7..
 */
@Controller
@Slf4j
public class ThymleafController {

    @Autowired
    private ConfigProperties configProperties;

    @RequestMapping("/thymeleaf")
    @ResponseBody
    public ModelAndView index()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("config", configProperties);
        log.info("config name/text, {}/{}", configProperties.getName(), configProperties.getText());
        return new ModelAndView("thymeleaf", map);
    }
}
