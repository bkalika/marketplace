package com.intellistart.marketplace.service;

import com.intellistart.marketplace.dto.ProductDTO;
import com.intellistart.marketplace.exception.ResourceNotFoundException;
import com.intellistart.marketplace.mapper.ProductMapper;
import com.intellistart.marketplace.model.Product;
import com.intellistart.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author @bkalika
 * Created on 22.07.2022 1:08 PM
 */

@Service
public class ProductService implements IProductService, Serializable {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElseThrow(() ->
                new ResourceNotFoundException("The product not found"));
    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
        Optional<Product> productOptional = productRepository.findByName(productDTO.getName());
        if(productOptional.isPresent()) {
            throw new ResourceNotFoundException("Product with this name has already exists! Enter another one!");
        }
        Product product = ProductMapper.DtoToEntity(productDTO);
        product = productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if(!exists) {
            throw new ResourceNotFoundException("The product does not exists!\n");
        }
        productRepository.deleteById(productId);
    }

}
