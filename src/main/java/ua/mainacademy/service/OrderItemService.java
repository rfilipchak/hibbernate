package ua.mainacademy.service;

import lombok.AllArgsConstructor;
import ua.mainacademy.dao.OrderItemDAO;
import ua.mainacademy.model.OrderItem;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@AllArgsConstructor
public class OrderItemService {

    private OrderItemDAO orderItemDAO;

    public OrderItem save(OrderItem orderItem) {
        if (nonNull(orderItem.getId())) {
            throw new RuntimeException("Create failed");
        }
        return orderItemDAO.save(orderItem);
    }

    public OrderItem update(OrderItem orderItem){
        if (isNull(orderItem.getId())) {
            throw new RuntimeException("Update is failed!");
        }
        return orderItemDAO.update(orderItem);
    }

    public void delete(OrderItem orderItem){
        if (isNull(orderItem.getId())) {
            throw new RuntimeException("Delete failed");
        }
        orderItemDAO.delete(orderItem);
    }

    public OrderItem getById(Integer id){
        if (isNull(id)) {
            throw new RuntimeException("Search is failed!");
        }
        return orderItemDAO.getById(id);
    }

}
