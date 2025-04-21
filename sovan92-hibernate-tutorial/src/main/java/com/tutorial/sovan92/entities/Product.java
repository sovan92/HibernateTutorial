package com.tutorial.sovan92.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "Product")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private int id;
    private String name;
    @Column(name = "description")
    private String desc;
    private double price;
}
