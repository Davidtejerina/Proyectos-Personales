package alexDavid.controller;

import alexDavid.dtos.OrderDTO.OrderRequestDto;
import alexDavid.dtos.OrderDTO.OrderResponseDto;
import alexDavid.mappers.OrderMapper;
import alexDavid.models.Order;
import alexDavid.service.OrderService.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/divehub/orders")
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
