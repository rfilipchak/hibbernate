package ua.mainacademy.service;

import lombok.AllArgsConstructor;
import ua.mainacademy.dao.OrderDAO;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.User;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@AllArgsConstructor
public class OrderService {

    private OrderDAO orderDAO;

    public Order save(Order order) {
        if (nonNull(order.getId())) {
            throw new RuntimeException("Create failed");
        }
        return orderDAO.save(order);
    }

    public Order update(Order order){
        if (isNull(order.getId())) {
            throw new RuntimeException("Update is failed!");
        }
        return orderDAO.update(order);
    }

    public void delete(Order order){
        if (isNull(order.getId())) {
            throw new RuntimeException("Delete failed");
        }
        orderDAO.delete(order);
    }

    public Order getById(Integer id){
        if (isNull(id)) {
            throw new RuntimeException("Search is failed!");
        }
        return orderDAO.getById(id);
    }

}
