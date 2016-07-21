package boot.service;

import boot.SpringBootDemoApplication;
import boot.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 1. 스프링 서비스를 이용한 테스트
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootDemoApplication.class)
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