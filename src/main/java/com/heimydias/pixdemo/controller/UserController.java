package com.heimydias.pixdemo.controller;

import com.heimydias.pixdemo.model.CreateTransactionRequest;
import com.heimydias.pixdemo.model.CreateUserRequest;
import com.heimydias.pixdemo.model.User;
import com.heimydias.pixdemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest userCreate) {
        User user = userService.createUser(userCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/pix")
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        String message = userService.createTransaction(createTransactionRequest);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
