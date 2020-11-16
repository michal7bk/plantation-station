package pl.bak.plantation.station.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.bak.plantation.station.customer.domain.Customer;
import pl.bak.plantation.station.customer.domain.dto.CustomerInfoDto;
import pl.bak.plantation.station.customer.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<CustomerInfoDto> findCustomers() {
        return customerService.findCustomers();
    }

    @GetMapping(value = "/{customerId}")
    public CustomerInfoDto findCustomer(@PathVariable Long customerId) {
        return customerService.findCustomer(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @DeleteMapping(value = "/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("customerId") Long customerId) {
        customerService.delete(customerId);
    }


}
