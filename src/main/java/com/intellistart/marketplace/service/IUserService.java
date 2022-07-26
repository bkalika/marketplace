package com.intellistart.marketplace.service;

import com.intellistart.marketplace.dto.UserDTO;
import com.intellistart.marketplace.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author @bkalika
 * Created on 22.07.2022 12:04 AM
 */

@Component
public interface IUserService {
    List<User> getUsers();
    User getUserById(Long userId);
    User addUser(UserDTO userDTO);
    void deleteUser(Long userId);
    User addProduct(Long userId, Long productId);
}
