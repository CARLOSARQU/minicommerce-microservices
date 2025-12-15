package com.tienda.order_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String orderNumber; // Un UUID para identificar el pedido
    
    @OneToMany(cascade = CascadeType.ALL) // Un pedido tiene muchos items
    private List<OrderLineItems> orderLineItemsList;
}