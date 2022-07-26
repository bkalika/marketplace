package com.intellistart.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author @bkalika
 * Created on 22.07.2022 12:30 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="product")
@JsonIgnoreProperties(value = {"users"})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;

    @Column(
            nullable = false,
            unique = true,
            length = 224
    )
    private String name;

    @ColumnDefault("1")
    @Column(nullable = false)
    private Long price;

    @ManyToMany(mappedBy = "products", cascade = {CascadeType.PERSIST})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<User> users;
}
