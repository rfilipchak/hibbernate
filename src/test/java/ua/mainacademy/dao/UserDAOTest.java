package ua.mainacademy.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.mainacademy.model.User;
import ua.mainacademy.service.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ua.mainacademy.prototype.Prototype.aNewUser;
import static ua.mainacademy.testsutil.ClearTestDB.clearDb;

class UserDAOTest {

    private UserService userService = new UserService(new UserDAO());

    @BeforeEach
    void setUp() {
        clearDb();
    }

    @AfterEach
    void tearDown() {
        clearDb();
    }

    @Test
    void saveUserTest() {
        User user = aNewUser();
        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
    }

    @Test
    void getUserByIdTest() {
        User user = aNewUser();
        User savedUser = userService.save(user);

        assertNotNull(savedUser.getId());
        Assertions.assertEquals(savedUser, userService.getById(savedUser.getId()));
    }

    @Test
    void updateUserTest() {
        User userForSaving = aNewUser();
        User savedUser = userService.save(userForSaving);

        assertNotNull(savedUser.getId());

        User userForUpdate = savedUser.toBuilder()
                .id(savedUser.getId())
                .firstName("Roman")
                .build();
        User updatedUser = userService.update(userForUpdate);

        Assertions.assertEquals(updatedUser, userForUpdate);
    }

    @Test
    void deleteUserTest() {
        User userForSaving = aNewUser();
        User savedUser = userService.save(userForSaving);

        assertNotNull(savedUser.getId());

        userService.delete(savedUser);

        Assertions.assertNull(userService.getById(savedUser.getId()));
    }
}