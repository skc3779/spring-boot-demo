package boot.json;

import boot.entity.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Json 유닛 테스트
 */
public class ItemJosnTests {

    private JacksonTester<Item> json;

    @Before
    public void setup() {
        ObjectMapper objectMappper = new ObjectMapper();
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMappper);

    }

    /**
     * Serialize 테스트
     * @throws Exception
     */
    @Test
    public void serializeJson() throws Exception {
        Item item = new Item(1L, "Item 01");
        assertThat(this.json.write(item)).isEqualToJson("/boot/json/item.json");

        assertThat(this.json.write(item)).hasJsonPathStringValue("@.text");

    }

    /**
     * Deserialize 테스트
     * @throws Exception
     */
    @Test
    public void deserializeJson() throws Exception {
        String itemString = "{\"id\":1, \"text\":\"Item 01\"}";
        assertThat(this.json.parse(itemString)).isEqualTo(new Item(1L, "Item 01"));

        assertThat(this.json.parseObject(itemString).getText()).isEqualTo("Item 01");
    }
}
