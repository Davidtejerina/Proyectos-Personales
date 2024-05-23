package alexDavid.service.OrderService;

import alexDavid.models.Order;


public interface OrderService {
    java.util.List<Order> findByUser(String email);
    Order addOrder(Order order);
    void deleteByUser(String email);
}