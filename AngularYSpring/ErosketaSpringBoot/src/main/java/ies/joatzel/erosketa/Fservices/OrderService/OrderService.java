package ies.joatzel.erosketa.Fservices.OrderService;

import ies.joatzel.erosketa.Amodels.Order;


public interface OrderService {
    java.util.List<Order> findByUser(String email);
    Order addOrder(Order order);
    void deleteByUser(String email);
}