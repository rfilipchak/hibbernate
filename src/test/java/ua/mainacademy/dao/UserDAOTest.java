package ua.mainacademy.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import ua.mainacademy.model.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ua.mainacademy.prototype.Prototype.aNewUser;
import static ua.mainacademy.testsutil.ClearTestDB.clearDb;

class UserDAOTest {

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
        UserDAO userDAO = new UserDAO();
        User savedUser = userDAO.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
    }

    @Test
    void getUserByIdTest() {
        User user = aNewUser();
        UserDAO userDAO = new UserDAO();

        User savedUser = userDAO.save(user);
        assertNotNull(savedUser.getId());

        Assertions.assertTrue(new ReflectionEquals(savedUser)
                .matches(userDAO.getById(savedUser.getId())));
    }

    @Test
    void updateUserTest() {
        User userForSaving = aNewUser();
        UserDAO userDAO = new UserDAO();

        User savedUser = userDAO.save(userForSaving);
        assertNotNull(savedUser.getId());

        User roman = savedUser.toBuilder()
                .id(savedUser.getId())
                .firstName("Roman")
                .build();
        User updatedUser = userDAO.update(roman);

        Assertions.assertTrue(new ReflectionEquals(savedUser, "firstName")
                .matches(updatedUser));
        Assertions.assertTrue(updatedUser.getFirstName().equals(roman.getFirstName()));
    }

    @Test
    void deleteUserTest() {
        User userForSaving = aNewUser();
        UserDAO userDAO = new UserDAO();

        User savedUser = userDAO.save(userForSaving);
        assertNotNull(savedUser.getId());

        userDAO.delete(savedUser);

        Assertions.assertNull(userDAO.getById(savedUser.getId()));
    }
}