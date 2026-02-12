package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.modal.Product;
import com.example.demo.modal.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Page<User> getUsers(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    @Override
    public User signup(User user) {

        // check if user already exists
        if (repo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists with this email");
        }

        // encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repo.save(user);
    }

    @Override
    public String login(String email, String password) {

        User user = repo.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(user.getEmail(), user.getRole());
        }
        return null;
    }

    @Override
    public String refreshAccessToken(String refreshToken) {

        // 1. validate refresh token
        String email = jwtUtil.extractEmail(refreshToken);

        User user = repo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("Invalid refresh token");
        }

        // 2. generate new access token
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }

    public User saveUser(User user) {
        return repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getByEmail(String email) {
        return repo.findByEmail(email);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }



}
