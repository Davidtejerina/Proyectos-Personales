package alexDavid.models;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED) //Se crean las tablas de las subclases, las cuales solo contienen las columnas específicas de esas subclases,
                                                //junto con una clave externa que hace referencia a la tabla de la superclase.
public abstract class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double starting_price;
    private Double final_price;
    private String image;
    private Category category;
    private String tag;
}
