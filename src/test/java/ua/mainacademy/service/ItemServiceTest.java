package ua.mainacademy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.mainacademy.dao.ItemDAO;
import ua.mainacademy.model.Item;

import static org.mockito.BDDMockito.then;
import static ua.mainacademy.prototype.Prototype.aNewItem;

class ItemServiceTest {

    ItemDAO itemDAO = Mockito.mock(ItemDAO.class);
    private ItemService itemService = new ItemService(itemDAO);


    @Test
    void save() {
        Item item = aNewItem();
        Mockito.when(itemDAO.save(item)).thenReturn(aNewItem().toBuilder().id(1).build());

        Item savedItem = itemService.save(item);

        Mockito.verify(itemDAO, Mockito.times(1)).save(item);
        Assertions.assertEquals(aNewItem().toBuilder().id(1).build(), savedItem);
    }

    @Test
    void update() {
        Item item = aNewItem().toBuilder().id(1).itemCode("123454321").build();
        Mockito.when(itemDAO.update(item)).thenReturn(aNewItem().toBuilder().id(1).itemCode("123454321").build());

        Item savedItem = itemService.update(item);
        Mockito.verify(itemDAO, Mockito.times(1)).update(item);
        Assertions.assertEquals(aNewItem().toBuilder().id(1).itemCode("123454321").build()
                , savedItem);
    }

    @Test
    void delete() {
        Item item = aNewItem().toBuilder().id(1).build();

        itemService.delete(item);
        Mockito.verify(itemDAO, Mockito.times(1)).delete(item);
    }

    @Test
    void getById() {
        int id = 1;
        Item item = aNewItem().toBuilder().id(id).build();

        Mockito.when(itemDAO.getById(id)).thenReturn(item);

        Item expected = itemService.getById(id);
        Mockito.verify(itemDAO, Mockito.times(1)).getById(1);
        Assertions.assertEquals(aNewItem().toBuilder().id(id).build()
                , expected);
    }

    @Test
    void shouldTrowAnExceptionForNullOnSaving() {
        Assertions.assertThrows(RuntimeException.class, () -> itemService.save(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnUpdating() {
        Assertions.assertThrows(RuntimeException.class, () -> itemService.update(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnGetting() {
        Assertions.assertThrows(RuntimeException.class, () -> itemService.getById(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnDeleting() {
        Assertions.assertThrows(RuntimeException.class, () -> itemService.delete(null));
    }
}