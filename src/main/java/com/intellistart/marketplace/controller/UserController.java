package com.intellistart.marketplace.controller;

import com.intellistart.marketplace.dto.UserDTO;
import com.intellistart.marketplace.model.Product;
import com.intellistart.marketplace.model.User;
import com.intellistart.marketplace.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * @author @bkalika
 * Created on 21.07.2022 11:56 PM
 */

@RestController
@RequestMapping(path="api/v1/users")
@Validated
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUserById(@Valid @PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        ResponseEntity<?> user = userService.addUser(userDTO);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<?> deleteUser(@Valid @PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok()
                .body(
                        Collections.singletonMap("response",
                                String.format("User with ID %s deleted successfully!", userId))
                );
    }

    @PostMapping("{userId}/products/{productId}")
    public ResponseEntity<?> createProductToUser(@NonNull
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId
            ) {
        User user = userService.addProduct(userId, productId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(user);
    }

    @GetMapping("{userId}/products")
    public ResponseEntity<?> getProductsByUser(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);
        List<Product> userProducts = user.getProducts();
        return ResponseEntity.ok().body(userProducts);
    }
}
