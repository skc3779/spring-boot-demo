package boot.repository;

import boot.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Repository 유닛 테스트
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class ItemRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    /**
     * Repository 테스트
     */
    @Test
    public void testFindById() {
        this.entityManager.persist(new Item(null, "Item 07"));
        Item item = this.itemRepository.findOne(7L);

        assertThat(item.getText()).isEqualTo("Item 07");
    }

    @Test
    public void testItem() {
        Item item = new Item(1L, "데이터 01");

        log.info(item.toString());

    }
}
