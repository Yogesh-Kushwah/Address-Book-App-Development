package com.bridgelebz.AddressBook.controller;

import com.bridgelebz.AddressBook.model.Address;
import com.bridgelebz.AddressBook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService service;

    @GetMapping
    public List<Address> getAll() {
        return service.getAllAddresses();
    }

    @PostMapping
    public Address create(@RequestBody Address address) {
        return service.saveAddress(address);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        return service.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteAddress(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
