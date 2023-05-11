package com.example.individual_spring.stores.security;


import com.example.individual_spring.stores.UserProfile;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public UserProfile toUser(PasswordEncoder passwordEncoder) {
        return new UserProfile(
                username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }
}
