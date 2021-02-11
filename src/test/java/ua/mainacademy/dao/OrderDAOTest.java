package ua.mainacademy.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.User;
import ua.mainacademy.service.OrderService;
import ua.mainacademy.service.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ua.mainacademy.prototype.Prototype.aNewOrder;
import static ua.mainacademy.prototype.Prototype.aNewUser;
import static ua.mainacademy.testsutil.ClearTestDB.clearDb;

class OrderDAOTest {

    private OrderService orderService = new OrderService(new OrderDAO());
    private UserService userService = new UserService(new UserDAO());
    private User user;

    @BeforeEach
    void setUp() {
        clearDb();
        user = getCreatedUser();
    }

    @AfterEach
    void tearDown() {
        clearDb();
    }

    @Test
    void saveOrderTest() {
        Order order = aNewOrder().toBuilder().user(user).build();
        Order savedOrder = orderService.save(order);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
    }

    @Test
    void getOrderByIdTest() {
        Order order = aNewOrder().toBuilder().user(user).build();
        Order savedOrder = orderService.save(order);

        assertNotNull(savedOrder.getId());
        Assertions.assertEquals(orderService.getById(savedOrder.getId()), savedOrder);
    }

    @Test
    void updateOrderTest() {
        Order order = aNewOrder().toBuilder().user(user).build();
        Order savedOrder = orderService.save(order);

        assertNotNull(savedOrder.getId());

        Order orderForUpdate = order.toBuilder()
                .id(savedOrder.getId())
                .status(Order.Status.CLOSED)
                .build();
        Order updatedOrder = orderService.update(orderForUpdate);

        Assertions.assertEquals(orderForUpdate, updatedOrder);
    }

    @Test
    void deleteOrderTest() {
        Order order = aNewOrder().toBuilder().user(user).build();

        Order savedOrder = orderService.save(order);
        assertNotNull(savedOrder.getId());

        orderService.delete(savedOrder);

        Assertions.assertNull(orderService.getById(savedOrder.getId()));
    }

    private User getCreatedUser() {
        User userForSaving = aNewUser();
        User savedUser = userService.save(userForSaving);

        assertNotNull(savedUser.getId());
        return savedUser;
    }
}