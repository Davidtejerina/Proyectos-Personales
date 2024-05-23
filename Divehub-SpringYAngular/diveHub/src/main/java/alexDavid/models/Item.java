package alexDavid.models;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item extends Product {
    private Double weight;
    private Integer stock;

    public Item(Long id, String name, String description, Double starting_price, Double final_price, String image,
                Double weight, Category category, String tag, Integer stock) {
      super(id, name, description, starting_price, final_price, image, category, tag);
      this.weight = weight;
      this.stock = stock;
    }
}
