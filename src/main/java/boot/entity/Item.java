package boot.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by kaha on 2014. 10. 8..
 */
@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String text;
}
