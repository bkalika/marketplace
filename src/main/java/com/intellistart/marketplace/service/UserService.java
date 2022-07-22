package com.intellistart.marketplace.service;

import com.intellistart.marketplace.dto.UserDTO;
import com.intellistart.marketplace.exception.ResourceNotFoundException;
import com.intellistart.marketplace.mapper.UserMapper;
import com.intellistart.marketplace.model.User;
import com.intellistart.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @author @bkalika
 * Created on 22.07.2022 12:03 AM
 */
@Service
public class UserService implements IUserService, Serializable {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() ->
                new ResourceNotFoundException("The user not found"));
    }

    @Override
    public ResponseEntity<?> addUser(@Valid UserDTO userDTO) {
        User user = UserMapper.DtoToEntity(userDTO);
        user = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId);
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new ResourceNotFoundException("The user does not exist\n");
        }
        userRepository.deleteById(userId);
    }
}
