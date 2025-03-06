package com.bridgelebz.addressBook.controller;

import com.bridgelebz.addressBook.Repository.AddressRepository;
import com.bridgelebz.addressBook.dto.AddressBookDTO;
import com.bridgelebz.addressBook.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<Address> create(@RequestBody AddressBookDTO dto) {
        Address address = new Address();
        address.setName(dto.getName());
        address.setPhoneNumber(dto.getPhoneNumber());
        address.setEmail(dto.getEmail());
        address.setCity(dto.getCity());

        return ResponseEntity.ok(repository.save(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setPhoneNumber(dto.getPhoneNumber());
            existing.setEmail(dto.getEmail());
            existing.setCity(dto.getCity());

            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
