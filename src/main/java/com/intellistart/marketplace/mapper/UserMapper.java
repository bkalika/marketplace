package com.intellistart.marketplace.mapper;

import com.intellistart.marketplace.dto.UserDTO;
import com.intellistart.marketplace.model.User;

/**
 * @author @bkalika
 * Created on 22.07.2022 12:45 PM
 */
public class UserMapper {
    public static User DtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAmountOfMoney(userDTO.getAmountOfMoney());
        return user;
    }
}
