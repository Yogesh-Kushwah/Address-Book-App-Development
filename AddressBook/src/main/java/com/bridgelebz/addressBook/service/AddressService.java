package com.bridgelebz.addressBook.service;

import com.bridgelebz.addressBook.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j  // Enables Lombok Logging
@Service
public class AddressService {
    private final List<Address> addressList = new ArrayList<>();
    private long idCounter = 1; // To generate unique IDs

    public List<Address> getAllAddresses() {
        log.info("Fetching all addresses");
        return addressList;
    }

    public Optional<Address> getAddressById(Long id) {
        log.info("Fetching address with ID: {}", id);
        return addressList.stream().filter(address -> address.getId().equals(id)).findFirst();
    }

    public Address saveAddress(Address address) {
        address.setId(idCounter++); // Assigning unique ID
        addressList.add(address);
        log.info("New address saved: {}", address);
        return address;
    }

    public Optional<Address> updateAddress(Long id, Address updatedAddress) {
        return getAddressById(id).map(existing -> {
            log.info("Updating address with ID: {}", id);
            existing.setName(updatedAddress.getName());
            existing.setPhoneNumber(updatedAddress.getPhoneNumber());
            existing.setEmail(updatedAddress.getEmail());
            existing.setCity(updatedAddress.getCity());
            return existing;
        });
    }

    public boolean deleteAddress(Long id) {
        log.info("Deleting address with ID: {}", id);
        return addressList.removeIf(address -> address.getId().equals(id));
    }
}
