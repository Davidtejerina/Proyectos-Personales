package ies.joatzel.erosketa.Fservices.OrderService;

import ies.joatzel.erosketa.Amodels.Order;
import ies.joatzel.erosketa.Crepository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> findByUser(String email) {
        return orderRepository.findByUser_Email(email);
    }


    @Override
    public Order addOrder(Order order) {
        order.setDate(java.time.LocalDateTime.now());
        return orderRepository.save(order);
    }


    @Override
    public void deleteByUser(String email) {
        this.findByUser(email);
        orderRepository.deleteByUser_Email(email);
    }
}