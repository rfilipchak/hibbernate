package ua.mainacademy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.mainacademy.dao.OrderItemDAO;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.OrderItem;

import static ua.mainacademy.prototype.Prototype.aNewOrder;
import static ua.mainacademy.prototype.Prototype.aNewOrderItem;

class OrderItemServiceTest {

    OrderItemDAO orderItemDAO = Mockito.mock(OrderItemDAO.class);
    private OrderItemService orderItemService = new OrderItemService(orderItemDAO);


    @Test
    void save() {
        OrderItem orderItem = aNewOrderItem();
        Mockito.when(orderItemDAO.save(orderItem)).thenReturn(aNewOrderItem().toBuilder().id(1).build());

        OrderItem savedOrderItem = orderItemService.save(orderItem);

        Mockito.verify(orderItemDAO, Mockito.times(1)).save(orderItem);
        Assertions.assertEquals(aNewOrderItem().toBuilder().id(1).build(), savedOrderItem);
    }

    @Test
    void update() {
        OrderItem orderItem = aNewOrderItem().toBuilder().id(1).build();
        Mockito.when(orderItemDAO.update(orderItem)).thenReturn(aNewOrderItem().toBuilder().id(1).build());

        OrderItem savedOrderItem = orderItemService.update(orderItem);
        Mockito.verify(orderItemDAO, Mockito.times(1)).update(orderItem);
        Assertions.assertEquals(aNewOrderItem().toBuilder().id(1).build()
                , savedOrderItem);
    }

    @Test
    void delete() {
        OrderItem orderItem = aNewOrderItem().toBuilder().id(1).build();

        orderItemService.delete(orderItem);
        Mockito.verify(orderItemDAO, Mockito.times(1)).delete(orderItem);
    }

    @Test
    void getById() {
        int id = 1;
        OrderItem orderItem = aNewOrderItem().toBuilder().id(1).build();

        Mockito.when(orderItemDAO.getById(id)).thenReturn(orderItem);

        OrderItem expected = orderItemService.getById(id);
        Mockito.verify(orderItemDAO, Mockito.times(1)).getById(1);
        Assertions.assertEquals(aNewOrderItem().toBuilder().id(id).build()
                , expected);
    }

    @Test
    void shouldTrowAnExceptionForNullOnSaving() {
        Assertions.assertThrows(RuntimeException.class, () -> orderItemService.save(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnUpdating() {
        Assertions.assertThrows(RuntimeException.class, () -> orderItemService.update(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnGetting() {
        Assertions.assertThrows(RuntimeException.class, () -> orderItemService.getById(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnDeleting() {
        Assertions.assertThrows(RuntimeException.class, () -> orderItemService.delete(null));
    }
}