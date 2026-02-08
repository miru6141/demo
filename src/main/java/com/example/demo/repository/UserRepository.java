package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.modal.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// public interface UserRepository extends JpaRepository<User, Long> {
//     User findByUsername(String username);a
// }


public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    Page<User> findAll(Pageable pageable);

}



