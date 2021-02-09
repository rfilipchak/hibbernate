package ua.mainacademy.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ua.mainacademy.prototype.Prototype.aNewOrder;
import static ua.mainacademy.prototype.Prototype.aNewUser;
import static ua.mainacademy.testsutil.ClearTestDB.clearDb;

class OrderDAOTest {

    private static User user = new User();

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
        OrderDAO orderDAO = new OrderDAO();
        Order savedOrder = orderDAO.save(order);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
    }

    @Test
    void getOrderByIdTest() {
        Order order = aNewOrder().toBuilder().user(user).build();
        OrderDAO orderDAO = new OrderDAO();
        Order savedOrder = orderDAO.save(order);

        assertNotNull(savedOrder.getId());

        Assertions.assertTrue(new ReflectionEquals(savedOrder)
                .matches(orderDAO.getById(savedOrder.getId())));
    }

    @Test
    void updateOrderTest() {
        Order order = aNewOrder().toBuilder().user(user).build();
        OrderDAO orderDAO = new OrderDAO();

        Order savedOrder = orderDAO.save(order);
        assertNotNull(savedOrder.getId());

        Order orderForUpdate = order.toBuilder()
                .id(savedOrder.getId())
                .status(Order.Status.CLOSED)
                .build();
        Order updatedOrder = orderDAO.update(orderForUpdate);

        Assertions.assertTrue(new ReflectionEquals(savedOrder, "status")
                .matches(updatedOrder));
        Assertions.assertTrue(updatedOrder.getStatus().equals(orderForUpdate.getStatus()));
    }

    @Test
    void deleteOrderTest() {
        Order order = aNewOrder().toBuilder().user(user).build();
        OrderDAO orderDAO = new OrderDAO();

        Order savedOrder = orderDAO.save(order);
        assertNotNull(savedOrder.getId());

        orderDAO.delete(savedOrder);

        Assertions.assertNull(orderDAO.getById(savedOrder.getId()));
    }

    private User getCreatedUser() {
        User userForSaving = aNewUser();
        UserDAO userDAO = new UserDAO();

        User savedUser = userDAO.save(userForSaving);
        assertNotNull(savedUser.getId());
        return savedUser;
    }

}