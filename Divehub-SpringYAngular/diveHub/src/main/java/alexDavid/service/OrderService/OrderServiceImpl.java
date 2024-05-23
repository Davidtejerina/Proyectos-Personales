package alexDavid.service.OrderService;

import alexDavid.models.Order;
import alexDavid.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
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