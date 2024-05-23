package ies.joatzel.erosketa.Amodels;

import ies.joatzel.erosketa.Amodels.User.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "my_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private double total;
    private LocalDateTime date;
    private String address;
}
