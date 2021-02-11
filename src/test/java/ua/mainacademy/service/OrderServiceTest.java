package ua.mainacademy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.mainacademy.dao.OrderDAO;
import ua.mainacademy.model.Item;
import ua.mainacademy.model.Order;

import static ua.mainacademy.prototype.Prototype.aNewItem;
import static ua.mainacademy.prototype.Prototype.aNewOrder;

class OrderServiceTest {

    OrderDAO orderDAO = Mockito.mock(OrderDAO.class);
    private OrderService orderService = new OrderService(orderDAO);

    @Test
    void save() {
        Order order = aNewOrder();
        Mockito.when(orderDAO.save(order)).thenReturn(aNewOrder().toBuilder().id(1).build());

        Order savedOrder = orderService.save(order);

        Mockito.verify(orderDAO, Mockito.times(1)).save(order);
        Assertions.assertEquals(aNewOrder().toBuilder().id(1).build(), savedOrder);
    }

    @Test
    void update() {
        Order order = aNewOrder().toBuilder().id(1).status(Order.Status.OPEN).build();
        Mockito.when(orderDAO.update(order)).thenReturn(aNewOrder().toBuilder().id(1).status(Order.Status.OPEN).build());

        Order savedOrder = orderService.update(order);
        Mockito.verify(orderDAO, Mockito.times(1)).update(order);
        Assertions.assertEquals(aNewOrder().toBuilder().id(1).status(Order.Status.OPEN).build()
                , savedOrder);
    }

    @Test
    void delete() {
        Order order = aNewOrder().toBuilder().id(1).build();

        orderService.delete(order);
        Mockito.verify(orderDAO, Mockito.times(1)).delete(order);
    }

    @Test
    void getById() {
        int id = 1;
        Order order = aNewOrder().toBuilder().id(1).build();

        Mockito.when(orderDAO.getById(id)).thenReturn(order);

        Order expected = orderService.getById(id);
        Mockito.verify(orderDAO, Mockito.times(1)).getById(1);
        Assertions.assertEquals(aNewOrder().toBuilder().id(id).build()
                , expected);
    }

    @Test
    void shouldTrowAnExceptionForNullOnSaving() {
        Assertions.assertThrows(RuntimeException.class, () -> orderService.save(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnUpdating() {
        Assertions.assertThrows(RuntimeException.class, () -> orderService.update(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnGetting() {
        Assertions.assertThrows(RuntimeException.class, () -> orderService.getById(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnDeleting() {
        Assertions.assertThrows(RuntimeException.class, () -> orderService.delete(null));
    }
}