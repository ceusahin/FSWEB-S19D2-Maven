package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Address with this ID does not exist.  ID: " + id));
    }

    @Override
    @Transactional
    public Address save(Address address) {
        // validate
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public void update(Long id, Address address) {
        Address existingAddress = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Can not find address with this ID. ID: " + id));
        existingAddress.setStreet(address.getStreet());
        existingAddress.setNo(address.getNo());
        existingAddress.setCity(address.getCity());
        existingAddress.setCountry(address.getCountry());
        existingAddress.setDescription(address.getDescription());

        addressRepository.save(existingAddress);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
