package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.modal.User;

public interface UserService {

    User saveUser(User user);

    User signup(User user);

    // login returns JWT token
    String login(String email, String password);

    String refreshAccessToken(String refreshToken);

    
      Page<User> getUsers(int page, int size);

    

    List<User> getAll();

    User getByEmail(String email);

    void delete(String id);
}
