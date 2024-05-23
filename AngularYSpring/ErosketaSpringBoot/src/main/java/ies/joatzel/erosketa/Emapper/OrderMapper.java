package ies.joatzel.erosketa.Emapper;

import ies.joatzel.erosketa.Amodels.Order;
import ies.joatzel.erosketa.Dto.OrderDto.OrderRequestDto;
import ies.joatzel.erosketa.Dto.OrderDto.OrderResponseDto;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper {
    public OrderResponseDto toResponse(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getUser(),
                order.getTotal(),
                order.getDate(),
                order.getAddress()
        );
    }

    public Order toModel(OrderRequestDto orderDTO) {
        return new Order(
                null,
                orderDTO.getUser(),
                orderDTO.getTotal(),
                orderDTO.getDate(),
                orderDTO.getAddress()
        );
    }
}
