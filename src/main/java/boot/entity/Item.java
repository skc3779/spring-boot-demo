package boot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kaha on 2014. 10. 8..
 */
@Entity
@Data
@ToString
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
}
