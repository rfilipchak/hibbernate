package ua.mainacademy.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.mainacademy.model.Item;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ua.mainacademy.prototype.Prototype.aNewItem;
import static ua.mainacademy.testsutil.ClearTestDB.clearDb;

class ItemDAOTest {

    @BeforeEach
    void setUp() {
        clearDb();
    }

    @AfterEach
    void tearDown() {
        clearDb();
    }

    @Test
    void saveItemTest() {
        Item item = aNewItem();
        ItemDAO itemDAO = new ItemDAO();
        Item savedItem = itemDAO.save(item);

        assertNotNull(savedItem);
        assertNotNull(savedItem.getId());
    }

    @Test
    void getItemByIdTest() {
        Item item = aNewItem();
        ItemDAO itemDAO = new ItemDAO();
        Item savedItem = itemDAO.save(item);

        assertNotNull(savedItem.getId());
        Assertions.assertEquals(itemDAO.getById(savedItem.getId()), savedItem);
    }

    @Test
    void updateItemTest() {
        Item item = aNewItem();
        ItemDAO itemDAO = new ItemDAO();

        Item savedItem = itemDAO.save(item);
        assertNotNull(savedItem.getId());

        Item itemForUpdate = aNewItem().toBuilder()
                .id(savedItem.getId())
                .itemCode("NewCode")
                .build();
        Item updatedItem = itemDAO.update(itemForUpdate);

        Assertions.assertEquals(itemForUpdate, updatedItem);
    }

    @Test
    void deleteUserTest() {
        Item item = aNewItem();
        ItemDAO itemDAO = new ItemDAO();

        Item savedItem = itemDAO.save(item);
        assertNotNull(savedItem.getId());

        itemDAO.delete(savedItem);

        Assertions.assertNull(itemDAO.getById(savedItem.getId()));
    }
}