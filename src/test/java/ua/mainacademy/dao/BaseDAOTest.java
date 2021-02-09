package ua.mainacademy.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BaseDAOTest {

    @Test
    void shouldTrowAnExceptionForNullOnSaving() {
        UserDAO userDAO = new UserDAO();
        Assertions.assertThrows(RuntimeException.class, () -> userDAO.save(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnUpdating() {
        OrderDAO orderDAO = new OrderDAO();
        Assertions.assertThrows(RuntimeException.class, () -> orderDAO.update(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnGetting() {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        Assertions.assertThrows(RuntimeException.class, () -> orderItemDAO.getById(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnDeleting() {
        ItemDAO orderDAO = new ItemDAO();
        Assertions.assertThrows(RuntimeException.class, () -> orderDAO.delete(null));
    }
}