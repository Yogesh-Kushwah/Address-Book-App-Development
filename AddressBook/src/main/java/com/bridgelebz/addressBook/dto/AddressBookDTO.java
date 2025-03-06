package com.bridgelebz.addressBook.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private String city;
}
