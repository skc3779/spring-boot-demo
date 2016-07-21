package boot.controller;

import boot.entity.Item;
import boot.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;

/**
 * 스프링 부트 1.4.0.RC1를 이용한 테스트 (Mock을 이용한 테스트)
 * 현재 Maven으로만 컴파일되며 Gradle 컴파일시 오류가 발생함.
 *
 *  @SpringBootTest을 이용과 Mock을 이용한 테스트 방식
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ListControllerTest04 {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    ItemService mockItemService;

    private String jsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    public void testOne() throws Exception {
        // 가짜 데이터 생성
        Long id = 1L;
        given(this.mockItemService.findById(id)).willReturn(new Item(id, "데이터 01"));

        // 가짜 데이터 조회
        Item responseItem = restTemplate.getForObject("/api/item/"+id, Item.class);

        assertThat(responseItem.getId(), is(equalTo(id)));
    }

}