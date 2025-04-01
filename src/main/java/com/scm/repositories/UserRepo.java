package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    // Custom query methods can be defined here if needed
    // For example, findByEmail(String email) to find a user by their email

    //extra method likhe sakte 

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email,String password); 

}
