package pl.bak.plantation.station;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.bak.plantation.station.customer.domain.Customer;
import pl.bak.plantation.station.customer.repository.CustomerRepository;
import pl.bak.plantation.station.order.domain.Order;
import pl.bak.plantation.station.order.repository.OrderRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TestDataInitializr implements ApplicationRunner {

    private final List<Customer> customers = new ArrayList<>();
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private Customer customer1;
    private Customer customer2;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    public TestDataInitializr(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        cleanTables();
        createCustomers();
        createOrders();
    }

    private void createOrders() {
        Order order1 = Order.builder()
                .completed(false)
                .customer(customer1)
                .creationTime(LocalDateTime.now())
                .completedTime(LocalDateTime.now().plusMinutes(1L))
                .additionalInfo("bla")
                .build();
        orderRepository.save(order1);
    }

    private void cleanTables() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }

    private void createCustomers() {
        customer1 = Customer.builder()
                .phone("123-321-231")
                .name("u")
                .surname("suru")
                .build();
        customer2 = Customer.builder()
                .phone("213-231-221")
                .name("user2")
                .surname("suruser2")
                .build();
        customers.addAll(Arrays.asList(customer1, customer2));

        for (Customer customer : customers) {
            customerRepository.save(customer);
        }
    }
}
