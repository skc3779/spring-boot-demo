package boot.controller;

import boot.config.ConfigProperties;
import boot.entity.Item;
import boot.repository.ItemRepository;
import boot.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaha on 2014. 10. 7..
 */
@Controller
@Slf4j
public class ListController {

    @Autowired
    private ConfigProperties configProperties;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView list()
    {
        List<Item> items = itemService.getAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("config", configProperties);
        map.put("items", items);
        return new ModelAndView("list", map);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ModelAndView write(@ModelAttribute Item item) {
        //itemRepository.save(item);
        return new ModelAndView("redirect:/list");
    }
}
