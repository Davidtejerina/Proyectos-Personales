package ies.joatzel.erosketa.Gcontroller;

import ies.joatzel.erosketa.Amodels.Order;
import ies.joatzel.erosketa.Dto.OrderDto.OrderResponseDto;
import ies.joatzel.erosketa.Dto.OrderDto.OrderRequestDto;
import ies.joatzel.erosketa.Emapper.OrderMapper;
import ies.joatzel.erosketa.Fservices.OrderService.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/{email}")
    public ResponseEntity<List<Order>> getOrderByUser(
            @PathVariable String email
    ){
        return ResponseEntity.ok(this.orderService.findByUser(email));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> postOrder(
            @RequestBody OrderRequestDto orderRequestDto
    ){
        Order order = orderService.addOrder(orderMapper.toModel(orderRequestDto));
        OrderResponseDto orderResponseDto = orderMapper.toResponse(order);
        return ResponseEntity.ok(orderResponseDto);
    }

    @DeleteMapping("/clean/{email}")
    public ResponseEntity<?> removeOrder(
            @PathVariable String email
    ){
        this.orderService.deleteByUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
