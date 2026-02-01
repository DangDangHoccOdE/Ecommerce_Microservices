package com.ecommerce.user.dtos.request;

import com.ecommerce.user.dtos.response.AddressDTO;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private AddressDTO address;
}
