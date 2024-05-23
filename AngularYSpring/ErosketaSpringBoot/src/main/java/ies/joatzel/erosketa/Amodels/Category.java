package ies.joatzel.erosketa.Amodels;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


/**
 * Clase Category POJO
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String color;
    private String image;
}
