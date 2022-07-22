package com.intellistart.marketplace.repository;

import com.intellistart.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author @bkalika
 * Created on 22.07.2022 12:06 AM
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
