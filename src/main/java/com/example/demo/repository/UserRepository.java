package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.modal.User;

// public interface UserRepository extends JpaRepository<User, Long> {
//     User findByUsername(String username);a
// }


public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
