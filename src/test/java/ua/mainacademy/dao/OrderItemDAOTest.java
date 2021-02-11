package ua.mainacademy.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.mainacademy.model.Item;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.OrderItem;
import ua.mainacademy.model.User;
import ua.mainacademy.service.ItemService;
import ua.mainacademy.service.OrderItemService;
import ua.mainacademy.service.OrderService;
import ua.mainacademy.service.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ua.mainacademy.prototype.Prototype.*;
import static ua.mainacademy.testsutil.ClearTestDB.clearDb;

class OrderItemDAOTest {

    private OrderItemService orderItemService = new OrderItemService(new OrderItemDAO());
    private OrderService orderService = new OrderService(new OrderDAO());
    private UserService userService = new UserService(new UserDAO());
    private ItemService itemService = new ItemService(new ItemDAO());

    private User user;
    private Order order;
    private Item item;


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

        OrderItem savedOrderItem = orderItemService.save(orderItem);

        assertNotNull(savedOrderItem);
        assertNotNull(savedOrderItem.getId());
    }

    @Test
    void getOrderItemByIdTest() {
        OrderItem orderItem = aNewOrderItem().toBuilder()
                .order(order)
                .item(item)
                .build();

        OrderItem savedOrderItem = orderItemService.save(orderItem);

        assertNotNull(savedOrderItem.getId());

        Assertions.assertEquals(savedOrderItem,
                orderItemService.getById(savedOrderItem.getId()));
    }

    @Test
    void updateOrderItemTest() {
        OrderItem orderItem = aNewOrderItem().toBuilder()
                .order(order)
                .item(item)
                .build();

        OrderItem savedOrderItem = orderItemService.save(orderItem);

        assertNotNull(savedOrderItem.getId());

        OrderItem orderItemForUpdate = orderItem.toBuilder()
                .amount(300500)
                .build();
        OrderItem updatedOrderItem = orderItemService.update(orderItemForUpdate);

        Assertions.assertEquals(orderItemForUpdate, updatedOrderItem);
    }

    @Test
    void deleteOrderTest() {
        OrderItem orderItem = aNewOrderItem().toBuilder()
                .order(order)
                .item(item)
                .build();

        OrderItem savedOrderItem = orderItemService.save(orderItem);

        assertNotNull(savedOrderItem.getId());

        orderItemService.delete(savedOrderItem);

        Assertions.assertNull(orderItemService.getById(savedOrderItem.getId()));
    }

    private User getCreatedUser() {
        User userForSaving = aNewUser();

        User savedUser = userService.save(userForSaving);
        assertNotNull(savedUser.getId());
        return savedUser;
    }

    private Item getCreatedItem() {
        Item item = aNewItem();
        Item savedItem = itemService.save(item);

        assertNotNull(savedItem.getId());
        return savedItem;
    }

    private Order getCreatedOrder(User user) {
        Order order = aNewOrder().toBuilder().user(user).build();
        Order savedOrder = orderService.save(order);

        assertNotNull(savedOrder.getId());
        return savedOrder;
    }
}