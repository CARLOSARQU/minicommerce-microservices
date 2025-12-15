package com.tienda.order_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_line_items")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String skuCode; // El código del producto (ej. "iPhone 15")
    private BigDecimal price;
    private Integer quantity;
}