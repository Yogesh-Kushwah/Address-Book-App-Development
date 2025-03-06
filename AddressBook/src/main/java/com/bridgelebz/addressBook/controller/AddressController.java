package com.bridgelebz.addressBook.controller;

import com.bridgelebz.addressBook.dto.AddressBookDTO;
import com.bridgelebz.addressBook.model.Address;
import com.bridgelebz.addressBook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService service; // Using Service Layer

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(service.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        Optional<Address> address = service.getAddressById(id);
        return address.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<Address> create(@RequestBody AddressBookDTO dto) {
        Address address = new Address();
        address.setName(dto.getName());
        address.setPhoneNumber(dto.getPhoneNumber());
        address.setEmail(dto.getEmail());
        address.setCity(dto.getCity());

        return ResponseEntity.ok(service.saveAddress(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        Address updatedAddress = new Address();
        updatedAddress.setName(dto.getName());
        updatedAddress.setPhoneNumber(dto.getPhoneNumber());
        updatedAddress.setEmail(dto.getEmail());
        updatedAddress.setCity(dto.getCity());

        return service.updateAddress(id, updatedAddress)
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
