package boot.repository;

import boot.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kaha on 2014. 10. 8..
 */

public interface ItemRepository extends JpaRepository<Item, Long> {
}
