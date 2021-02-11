package ua.mainacademy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.mainacademy.dao.UserDAO;
import ua.mainacademy.model.Item;
import ua.mainacademy.model.User;

import static ua.mainacademy.prototype.Prototype.aNewItem;
import static ua.mainacademy.prototype.Prototype.aNewUser;

class UserServiceTest {

    UserDAO userDAO = Mockito.mock(UserDAO.class);
    private UserService userService = new UserService(userDAO);

    @Test
    void save() {
        User user = aNewUser();
        Mockito.when(userDAO.save(user)).thenReturn(aNewUser().toBuilder().id(1).build());

        User savedUser = userService.save(user);

        Mockito.verify(userDAO, Mockito.times(1)).save(user);
        Assertions.assertEquals(aNewUser().toBuilder().id(1).build(), savedUser);
    }

    @Test
    void update() {
        User user = aNewUser().toBuilder().id(1).firstName("123454321").build();
        Mockito.when(userDAO.update(user)).thenReturn(aNewUser().toBuilder().id(1).login("123454321").build());

        User savedUser = userService.update(user);
        Mockito.verify(userDAO, Mockito.times(1)).update(user);
        Assertions.assertEquals(aNewUser().toBuilder().id(1).login("123454321").build()
                , savedUser);
    }

    @Test
    void delete() {
        User user = aNewUser().toBuilder().id(1).build();

        userService.delete(user);
        Mockito.verify(userDAO, Mockito.times(1)).delete(user);
    }

    @Test
    void getById() {
        int id = 1;
        User user = aNewUser().toBuilder().id(id).build();

        Mockito.when(userDAO.getById(id)).thenReturn(user);

        User expected = userService.getById(id);
        Mockito.verify(userDAO, Mockito.times(1)).getById(1);
        Assertions.assertEquals(aNewUser().toBuilder().id(id).build()
                , expected);
    }

    @Test
    void shouldTrowAnExceptionForNullOnSaving() {
        Assertions.assertThrows(RuntimeException.class, () -> userService.save(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnUpdating() {
        Assertions.assertThrows(RuntimeException.class, () -> userService.update(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnGetting() {
        Assertions.assertThrows(RuntimeException.class, () -> userService.getById(null));
    }

    @Test
    void shouldTrowAnExceptionForNullOnDeleting() {
        Assertions.assertThrows(RuntimeException.class, () -> userService.delete(null));
    }
}