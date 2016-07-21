package boot.service;

import boot.entity.Item;

import java.util.List;

/**
 * Created by kaha on 2014. 10. 8..
 */
public interface ItemService {
    List<Item> getAll();
    Item save(Item item);
    Item findById(Long id);
}
