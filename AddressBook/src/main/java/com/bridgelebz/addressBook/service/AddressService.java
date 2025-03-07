package com.bridgelebz.addressBook.service;

import com.bridgelebz.addressBook.exception.EmployeeNotFoundException;
import com.bridgelebz.addressBook.model.Address;
import com.bridgelebz.addressBook.Repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public List<Address> getAllAddresses() {
        log.info("Fetching all addresses from DB");
        return repository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        log.info("Fetching address with ID: {}", id);
        return repository.findById(id);
    }

    public Address saveAddress(Address address) {
        log.info("Saving new address: {}", address);
        return repository.save(address);
    }


    public Address updateAddress(Long id, Address updatedAddress) {
        Address existing = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        log.info("Updating address with ID: {}", id);
        existing.setName(updatedAddress.getName());
        existing.setPhoneNumber(updatedAddress.getPhoneNumber());
        existing.setEmail(updatedAddress.getEmail());
        existing.setAddress(updatedAddress.getAddress());
        existing.setCity(updatedAddress.getCity());
        existing.setState(updatedAddress.getState());
        existing.setZip(updatedAddress.getZip());

        return repository.save(existing);
    }


    public boolean deleteAddress(Long id) {
        if (!repository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
        log.info("Deleting address with ID: {}", id);
        repository.deleteById(id);

        return false;
    }
}