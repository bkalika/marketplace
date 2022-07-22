package com.intellistart.marketplace.dto;

import com.intellistart.marketplace.validation.NameUpper;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * @author @bkalika
 * Created on 22.07.2022 12:43 AM
 */

@Data
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "first_name can not be null")
    @Size(max = 128, message = "first_name must be less than 128 characters")
    @NameUpper(message = "first_name must start from Upper case")
    @NotBlank(message = "first_name is required!")
    private String firstName;

    @NotNull(message = "last_name can not be null")
    @Size(max = 128, message = "last_name must be less than 128 characters")
    @NameUpper(message = "last_name must start from Upper case")
    @NotBlank(message = "last_name is required!")
    private String lastName;

    @PositiveOrZero
    @NotNull(message = "amount_of_money can not be null.")
    private Long amountOfMoney;
}
