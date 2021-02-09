package ua.mainacademy.prototype;

import ua.mainacademy.model.Item;
import ua.mainacademy.model.Order;
import ua.mainacademy.model.OrderItem;
import ua.mainacademy.model.User;

public class Prototype {

    public static User aNewUser() {
        return User.builder()
                .login("Login")
                .password("password")
                .firstName("name")
                .lastName("last_name")
                .build();
    }

    public static Item aNewItem() {
        return Item.builder()
                .itemCode("code")
                .name("itemName")
                .initPrice(1)
                .build();
    }

    public static Order aNewOrder(){
        return Order.builder()
                .creationTime(12345L)
                .status(Order.Status.OPEN)
                .user(aNewUser())
                .build();
    }

    public static OrderItem aNewOrderItem(){
        return OrderItem.builder()
                .amount(100500)
                .build();
    }
}
