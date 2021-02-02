package pl.bak.plantation.station.order.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.bak.plantation.station.customer.domain.Customer;
import pl.bak.plantation.station.customer.repository.CustomerRepository;
import pl.bak.plantation.station.exception.ObjectNotFoundException;
import pl.bak.plantation.station.order.domain.Order;
import pl.bak.plantation.station.order.domain.dto.NewOrderDto;
import pl.bak.plantation.station.order.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    final private OrderRepository orderRepository;
    final private CustomerRepository customerRepository;
    final private ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public Order save(NewOrderDto newOrder) {
        final Order order = modelMapper.map(newOrder, Order.class);
        order.setCreationTime(LocalDateTime.now());
        final Customer customer = customerRepository.findById(newOrder.getCustomerId())
                .orElseThrow(() -> new ObjectNotFoundException(Customer.class, newOrder.getCustomerId()));
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    public Order find(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Order.class, id));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Order completeOrder(Long orderId) {
        final Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException(Order.class, orderId));
        order.setCompleted(true);
        order.setCompletedTime(LocalDateTime.now());
        orderRepository.save(order);
        return order;
    }
}
