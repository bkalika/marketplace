package com.intellistart.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author @bkalika
 * Created on 21.07.2022 11:57 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="usr")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "usr_sequence",
            sequenceName = "usr_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usr_sequence"
    )
    private Long id;

    @Column(
            nullable = false,
            length = 128
    )
    private String firstName;

    @Column(
            nullable = false,
            length = 128
    )
    private String lastName;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Long amountOfMoney;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_product", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
