package boot.repository;

import boot.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by kaha on 2014. 10. 8..
 */

public interface ItemRepository
        extends JpaRepository<Item, Long> {
}
