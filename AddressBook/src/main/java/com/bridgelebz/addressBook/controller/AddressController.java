package com.bridgelebz.addressBook.controller;

import com.bridgelebz.addressBook.dto.AddressBookDTO;
import com.bridgelebz.addressBook.model.Address;
import com.bridgelebz.addressBook.service.AddressService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j  // Enables Lombok Logging
@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        log.info("Received GET request for all addresses");
        return ResponseEntity.ok(service.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        log.info("Received GET request for address ID: {}", id);
        Optional<Address> address = service.getAddressById(id);
        return address.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<Address> create(@Valid @RequestBody AddressBookDTO dto) {
        log.info("Received POST request to create new address");
        Address address = new Address();
        address.setName(dto.getName());
        address.setPhoneNumber(dto.getPhoneNumber());
        address.setEmail(dto.getEmail());
        address.setAddress(dto.getAddress());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZip(dto.getZip());

        return ResponseEntity.ok(service.saveAddress(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @Valid @RequestBody Address dto) {
        log.info("Received PUT request to update address ID: {}", id);
        Optional<Address> updatedAddress = service.updateAddress(id, dto);
        return updatedAddress.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Received DELETE request for address ID: {}", id);
        if (service.deleteAddress(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
