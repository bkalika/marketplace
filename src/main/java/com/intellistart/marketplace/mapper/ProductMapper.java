package com.intellistart.marketplace.mapper;

import com.intellistart.marketplace.dto.ProductDTO;
import com.intellistart.marketplace.model.Product;

/**
 * @author @bkalika
 * Created on 22.07.2022 1:30 PM
 */
public class ProductMapper {
    public static Product DtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
