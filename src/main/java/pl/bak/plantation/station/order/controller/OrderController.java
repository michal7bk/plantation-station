package pl.bak.plantation.station.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.bak.plantation.station.order.domain.Order;
import pl.bak.plantation.station.order.domain.dto.NewOrderDto;
import pl.bak.plantation.station.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{orderId}")
    public Order findOrder(@PathVariable Long orderId) {
        return orderService.find(orderId);
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody NewOrderDto order) {
        return orderService.save(order);
    }

    @PatchMapping(value = "/{orderId}/complete")
    @ResponseStatus(HttpStatus.OK)
    public Order completeOrder(@PathVariable Long orderId){
        return orderService.completeOrder(orderId);
    }

    @DeleteMapping(value = "/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("orderId") Long orderId) {
        orderService.delete(orderId);
    }

}
