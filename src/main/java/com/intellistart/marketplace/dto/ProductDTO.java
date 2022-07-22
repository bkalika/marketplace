package com.intellistart.marketplace.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * @author @bkalika
 * Created on 22.07.2022 1:10 PM
 */

@Data
@NoArgsConstructor
public class ProductDTO {

    @NotNull(message = "name can not be null")
    @Size(max = 128, message = "name must be less 128 characters")
    private String name;

    @Positive
    @NotNull(message = "price can not be null.")
    private Long price;
}
