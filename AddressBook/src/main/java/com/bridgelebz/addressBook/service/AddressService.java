package com.bridgelebz.addressBook.service;

import com.bridgelebz.addressBook.Repository.AddressRepository;
import com.bridgelebz.addressBook.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repository;

    public List<Address> getAllAddresses() {
        return repository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return repository.findById(id);
    }

    public Address saveAddress(Address address) {
        return repository.save(address);
    }

    public void deleteAddress(Long id) {
        repository.deleteById(id);
    }
}
