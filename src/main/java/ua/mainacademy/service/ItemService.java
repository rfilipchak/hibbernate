package ua.mainacademy.service;

import lombok.AllArgsConstructor;
import ua.mainacademy.dao.ItemDAO;
import ua.mainacademy.model.Item;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@AllArgsConstructor
public class ItemService {

    private ItemDAO itemDAO;

    public Item save(Item item) {
        if (nonNull(item.getId())) {
            throw new RuntimeException("Create failed");
        }
        return itemDAO.save(item);
    }

    public Item update(Item item){
        if (isNull(item.getId())) {
            throw new RuntimeException("Update is failed!");
        }
        return itemDAO.update(item);
    }

    public void delete(Item item){
        if (isNull(item.getId())) {
            throw new RuntimeException("Delete failed");
        }
        itemDAO.delete(item);
    }

    public Item getById(Integer id){
        if (isNull(id)) {
            throw new RuntimeException("Search is failed!");
        }
        return itemDAO.getById(id);
    }
}
