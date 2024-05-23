package ies.joatzel.erosketa.Amodels;

import jakarta.persistence.*;
import lombok.*;


/**
 * Clase Product POJO
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String image;

    @ManyToOne
    private Category category;
}

