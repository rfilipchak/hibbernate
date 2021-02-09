package ua.mainacademy.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import ua.mainacademy.model.Item;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.OrderItem;
import ua.mainacademy.model.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ua.mainacademy.prototype.Prototype.*;
import static ua.mainacademy.testsutil.ClearTestDB.clearDb;

class OrderItemDAOTest {

    private static User user = new User();
    private static Order order = new Order();
    private static Item item = new Item();


    @BeforeEach
    void setUp() {
        clearDb();
        user = getCreatedUser();
        order = getCreatedOrder(user);
        item = getCreatedItem();
    }

    @AfterEach
    void tearDown() {
        clearDb();
    }

    @Test
    void saveOrderItemTest() {
        OrderItem orderItem = aNewOrderItem().toBuilder()
                .order(order)
                .item(item)
                .build();


        OrderItemDAO orderItemDAO = new OrderItemDAO();
        OrderItem savedOrderItem = orderItemDAO.save(orderItem);

        assertNotNull(savedOrderItem);
        assertNotNull(savedOrderItem.getId());
    }

    @Test
    void getOrderItemByIdTest() {
        OrderItem orderItem = aNewOrderItem().toBuilder()
                .order(order)
                .item(item)
                .build();

        OrderItemDAO orderItemDAO = new OrderItemDAO();
        OrderItem savedOrderItem = orderItemDAO.save(orderItem);

        assertNotNull(savedOrderItem.getId());

        Assertions.assertTrue(new ReflectionEquals(savedOrderItem)
                .matches(orderItemDAO.getById(savedOrderItem.getId())));
    }

    @Test
    void updateOrderItemTest() {
        OrderItem orderItem = aNewOrderItem().toBuilder()
                .order(order)
                .item(item)
                .build();

        OrderItemDAO orderItemDAO = new OrderItemDAO();
        OrderItem savedOrderItem = orderItemDAO.save(orderItem);

        assertNotNull(savedOrderItem.getId());

        OrderItem orderItemForUpdate = orderItem.toBuilder()
                .amount(300500)
                .build();
        OrderItem updatedOrderItem = orderItemDAO.update(orderItemForUpdate);

        Assertions.assertTrue(new ReflectionEquals(savedOrderItem, "amount")
                .matches(updatedOrderItem));
        Assertions.assertTrue(updatedOrderItem.getAmount()
                .equals(orderItemForUpdate.getAmount()));
    }

    @Test
    void deleteOrderTest() {
        OrderItem orderItem = aNewOrderItem().toBuilder()
                .order(order)
                .item(item)
                .build();

        OrderItemDAO orderItemDAO = new OrderItemDAO();
        OrderItem savedOrderItem = orderItemDAO.save(orderItem);

        assertNotNull(savedOrderItem.getId());

        orderItemDAO.delete(savedOrderItem);

        Assertions.assertNull(orderItemDAO.getById(savedOrderItem.getId()));
    }

    private User getCreatedUser() {
        User userForSaving = aNewUser();
        UserDAO userDAO = new UserDAO();

        User savedUser = userDAO.save(userForSaving);
        assertNotNull(savedUser.getId());
        return savedUser;
    }

    private Item getCreatedItem() {
        Item item = aNewItem();
        ItemDAO itemDAO = new ItemDAO();
        Item savedItem = itemDAO.save(item);

        assertNotNull(savedItem.getId());
        return savedItem;
    }

    private Order getCreatedOrder(User user) {
        Order order = aNewOrder().toBuilder().user(user).build();
        OrderDAO orderDAO = new OrderDAO();
        Order savedOrder = orderDAO.save(order);

        assertNotNull(savedOrder.getId());
        return savedOrder;
    }

}