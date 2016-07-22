package boot.controller;

import boot.SpringBootDemoApplication;
import boot.entity.Item;
import boot.repository.ItemRepository;
import boot.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 2. 스프링 부트 1.3의  @WebIntegrationTest를 이용한 테스트
 * @WebIntegrationTest = @IntegrationTest + @WebAppConfiguration
 *
 * @WebIntegrationTest은 스프링 부트 1.4에서 deprecated 될 예정임.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootDemoApplication.class)
@ActiveProfiles("test")
//@WebIntegrationTest("server.port=0")
@WebIntegrationTest(randomPort=true)
@Slf4j
public class ListControllerTest02 {

    RestTemplate restTemplate;

    @Autowired
    ItemService itemService;

    @Value("${local.server.port}")
    int port;

    private String baseUrl;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        baseUrl = "http://localhost:" +  String.valueOf(port);
    }

    @Test
    public void testList() throws Exception {
        URI uri = URI.create(baseUrl+ "/api/list");
        List<Item> Items = Arrays.asList(restTemplate.getForObject(uri, Item[].class));
        assertThat(Items.size(), is(equalTo(itemService.getAll().size())));
    }

    @Test
    public void testNew() throws Exception {

        URI uri = URI.create(baseUrl+ "/api/new");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Item newItem = new Item(null, "데이터 07");
        HttpEntity<Item> entity = new HttpEntity(newItem, headers);

        ResponseEntity<Item> responseItem = restTemplate.exchange(uri, HttpMethod.POST, entity, Item.class);

        assertThat(responseItem.getBody().getText(), is(equalTo(newItem.getText())));
    }
}