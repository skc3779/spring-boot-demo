package boot.controller;

import boot.config.ConfigProperties;
import boot.entity.Item;
import boot.repository.ItemRepository;
import boot.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaha on 2014. 10. 7..
 */
@Controller
@Slf4j
public class ItemController {

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
    public ModelAndView write(@ModelAttribute Item item) {
        itemService.save(item);
        return new ModelAndView("redirect:/list");
    }

    @RequestMapping(value = "/api/list", method = RequestMethod.GET)
    @ResponseBody
    public Object apiList()
    {
        return itemService.getAll();
    }

    @RequestMapping(value = "/api/new", method = RequestMethod.POST)
    @ResponseBody
    public Object apiSave(@RequestBody Item item)
    {
        return itemService.save(item);
    }

    @RequestMapping(value = "/api/item/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object apiItem(@PathVariable(value = "id") Long id)
    {
        return itemService.findById(id);
    }

}
