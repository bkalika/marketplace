package com.intellistart.marketplace.repository;

import com.intellistart.marketplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author @bkalika
 * Created on 22.07.2022 1:26 PM
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
