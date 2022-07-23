package com.intellistart.marketplace.service;

import com.intellistart.marketplace.dto.UserDTO;
import com.intellistart.marketplace.exception.ResourceNotFoundException;
import com.intellistart.marketplace.mapper.UserMapper;
import com.intellistart.marketplace.model.Product;
import com.intellistart.marketplace.model.User;
import com.intellistart.marketplace.repository.ProductRepository;
import com.intellistart.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author @bkalika
 * Created on 22.07.2022 12:03 AM
 */
@Service
public class UserService implements IUserService, Serializable {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
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
    public User addUser(@Valid UserDTO userDTO) {
        User user = UserMapper.DtoToEntity(userDTO);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new ResourceNotFoundException("The user does not exist\n");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User addProduct(Long userId, Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        Optional<User> userOptional = userRepository.findById(userId);

        Product product = productOptional.orElseThrow(
                () -> new ResourceNotFoundException("The product does not exist\\n")
        );

        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("The user does not exist\n"));
        if(user.getAmountOfMoney() >= product.getPrice()) {
            user.getProducts().add(product);
            user.setAmountOfMoney(user.getAmountOfMoney() - product.getPrice());
            userRepository.save(user);

        } else {
            throw new ResourceNotFoundException("Not enough amount of money.");
        }


        return user;
    }

}
