package com.example.airxelerateapi.repository;

import com.example.airxelerateapi.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
