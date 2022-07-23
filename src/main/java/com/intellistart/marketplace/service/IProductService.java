package com.intellistart.marketplace.service;

import com.intellistart.marketplace.dto.ProductDTO;
import com.intellistart.marketplace.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author @bkalika
 * Created on 22.07.2022 1:09 PM
 */

@Component
public interface IProductService {
    List<Product> getProducts();
    Product getProductById(Long productId);
    Product addProduct(ProductDTO productDTO);
    void deleteProduct(Long productId);
}
