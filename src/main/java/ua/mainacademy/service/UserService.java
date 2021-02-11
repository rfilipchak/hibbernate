package ua.mainacademy.service;

import lombok.AllArgsConstructor;
import ua.mainacademy.dao.UserDAO;
import ua.mainacademy.model.User;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@AllArgsConstructor
public class UserService {

    private UserDAO userDAO;

    public User save(User user) {
        if (nonNull(user.getId())) {
            throw new RuntimeException("Create failed");
        }
        return userDAO.save(user);
    }

    public User update(User user){
        if (isNull(user.getId())) {
            throw new RuntimeException("Update is failed!");
        }
        return userDAO.update(user);
    }

    public void delete(User user){
        if (isNull(user.getId())) {
            throw new RuntimeException("Delete failed");
        }
        userDAO.delete(user);
    }

    public User getById(Integer id){
        if (isNull(id)) {
            throw new RuntimeException("Search is failed!");
        }
        return userDAO.getById(id);
    }
}
