package com.intellistart.marketplace.controller;

import com.intellistart.marketplace.dto.ProductDTO;
import com.intellistart.marketplace.model.Product;
import com.intellistart.marketplace.model.User;
import com.intellistart.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * @author @bkalika
 * Created on 22.07.2022 1:07 PM
 */

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("{productId}")
    public ResponseEntity<Product> getProductById(@Valid @PathVariable("productId") Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productService.addProduct(productDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{productId}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(product);
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<?> deleteProduct(@Valid @PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok()
                .body(
                        Collections.singletonMap("response",
                                String.format("Product with ID %s deleted successfully!", productId))
                );
    }

    @GetMapping("{productId}/users")
    public ResponseEntity<?> getUsersByProduct(@PathVariable("productId") Long productId) {
        Product product = productService.getProductById(productId);
        List<User> users = product.getUsers();
        return ResponseEntity.ok().body(users);
    }
}
