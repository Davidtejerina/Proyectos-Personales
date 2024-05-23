package ies.joatzel.erosketa.Dto.OrderDto;


import ies.joatzel.erosketa.Amodels.User.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class OrderResponseDto {
    private final Long id;
    private final User user;
    private final double total;
    private final LocalDateTime date;
    private final String address;
}
