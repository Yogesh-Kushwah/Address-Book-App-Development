package com.bridgelebz.AddressBook.service;

import com.bridgelebz.AddressBook.model.Address;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final List<Address> addressList = new ArrayList<>();
    private long nextId = 1; // To generate unique IDs

    public List<Address> getAllAddresses() {
        return addressList;
    }

    public Address saveAddress(Address address) {
        address.setId(nextId++);
        addressList.add(address);
        return address;
    }

    public Optional<Address> getAddressById(Long id) {
        return addressList.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public boolean deleteAddress(Long id) {
        return addressList.removeIf(a -> a.getId().equals(id));
    }
}

