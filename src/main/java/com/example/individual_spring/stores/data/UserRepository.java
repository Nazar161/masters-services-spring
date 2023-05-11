package com.example.individual_spring.stores.data;

import com.example.individual_spring.stores.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserProfile, Long> {
    UserProfile findByUsername(String username);
}
