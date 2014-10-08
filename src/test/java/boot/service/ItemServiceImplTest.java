package boot.service;

import boot.Application;
import boot.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void testGetAll() throws Exception {
        List<Item> items =  itemService.getAll();
        assertThat(items.size(), is(6));
    }
}