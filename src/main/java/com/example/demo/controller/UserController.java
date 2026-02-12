package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;




// public class UserController {

//     // @GetMapping("/hello")
//     // public String hello() {
//     //     return "Hello from Spring Boot2";
//     // }

//     // @PostMapping("/hello")
//     // public String postHello(@RequestBody String name) {
//     //     return "Hello " + name;
//     // }

//     @PostMapping("path")
//     public String thisISamairul(@RequestBody String entity) {
//         

//         return "hello this is here" + entity;
//     }

//     // @GetMapping("/hello")
//     // public ResponseEntity<String> hello() {
//     //     return ResponseEntity.ok("Hello Amairul");
//     // }

//     @PostMapping("/hello")
//     public ResponseEntity<String> postHello2(@RequestBody  String name1) {
//         return ResponseEntity.status(HttpStatus.CREATED).body("hello ji" + name1);
//     }
    
//     @PostMapping("/json")
//     public ResponseEntity <String> postJson(@RequestBody Map<String,String> data){
//         String name= data.get("name");
//         return ResponseEntity.ok("this is the"+name);
//     }

// }

// @RestController
// @RequestMapping("/user")
// public class UserController {

//     @Autowired
//     private UserService service;

//     @PostMapping
//     public User save(@RequestBody User user) {
//         return service.saveUser(user);
//     }

//     @GetMapping("/{username}")
//     public User get(@PathVariable String username) {
//         return service.getUser(username);
//     }
// }

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        return service.signup(user);
    }
    //
@PostMapping("/login")
public String login(@RequestBody Map<String,String> data){
    return service.login(data.get("email"), data.get("password"));
}

@GetMapping("/test")
public String getMethodName(@RequestParam String param) {
    return "this is amairul";
}

@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin/test")
public String adminTest() {
    return "Admin access only";
}

@PostMapping("/refresh")
public String refreshToken(@RequestBody String refreshToken) {
    return service.refreshAccessToken(refreshToken);
}

@GetMapping("/users")
public Page<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {

    return service.getUsers(page, size);
}




}


