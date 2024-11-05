package com.evantagesoft.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
