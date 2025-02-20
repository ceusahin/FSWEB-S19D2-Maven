package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer find(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Customer with this ID does not exist.  ID: " + id));
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void update(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Can not find customer with this ID. ID: " + id));
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setSalary(customer.getSalary());
        existingCustomer.setAddress(customer.getAddress());

        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer delete(Long id) {
        Optional<Customer> deletedCustomerOpt = customerRepository.findById(id);
        if (deletedCustomerOpt.isPresent()){
            customerRepository.delete(deletedCustomerOpt.get());
            return deletedCustomerOpt.get();
        } else {
            return null;
        }
    }
}
