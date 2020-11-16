package pl.bak.plantation.station.customer.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.bak.plantation.station.customer.domain.Customer;
import pl.bak.plantation.station.customer.domain.dto.CustomerInfoDto;
import pl.bak.plantation.station.customer.repository.CustomerRepository;
import pl.bak.plantation.station.exception.ConflictWithExistingException;
import pl.bak.plantation.station.exception.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    final private CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public List<CustomerInfoDto> findCustomers() {
        return customerRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CustomerInfoDto findCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Customer.class, id));
        return convertToDto(customer);
    }

    public Customer save(Customer customer) {
        if (!customerRepository.findBySurnameAndName(customer.getSurname(), customer.getName()).isPresent()) {
            return customerRepository.save(customer);
        } else throw new ConflictWithExistingException(Customer.class, customer.getId());

    }

    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Customer.class, id));
        customerRepository.delete(customer);
    }

    private CustomerInfoDto convertToDto(Customer customer) {
        return modelMapper.map(customer, CustomerInfoDto.class);
    }

}
