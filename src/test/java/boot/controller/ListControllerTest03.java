package boot.controller;

import boot.SpringBootDemoApplication;
import boot.entity.Item;
import boot.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * 스프링 부트 1.4.0.RC1를 이용한 테스트
 * 현재 Maven으로만 컴파일되며 Gradle 컴파일시 오류가 발생함.
 *
 * @SpringBootTest을 이용한 테스트 방식
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ListControllerTest03 {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ItemService itemService;

    private String jsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    public void testList() throws Exception {
        List<Item> Items = Arrays.asList(restTemplate.getForObject("/api/list", Item[].class));
        assertThat(Items.size(), is(equalTo(itemService.getAll().size())));
    }

    @Test
    public void testNew() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Item newItem = new Item(null, "데이터 07");
        HttpEntity<Item> entity = new HttpEntity(newItem, headers);

        ResponseEntity<Item> responseItem = restTemplate.exchange("/api/new", HttpMethod.POST, entity, Item.class);

        assertThat(responseItem.getBody().getText(), is(equalTo(newItem.getText())));
    }
}