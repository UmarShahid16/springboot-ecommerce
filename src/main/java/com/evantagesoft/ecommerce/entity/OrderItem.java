package com.evantagesoft.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item") // Using 'order_item' for table name is correct
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 'ManyToOne' relationship with Order
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false) // FK column in order_item table
    private Order order; // Property that refers back to Order

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // FK column for Product
    private Product product;

    private int quantity;
}
