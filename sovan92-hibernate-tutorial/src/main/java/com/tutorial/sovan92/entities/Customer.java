package com.tutorial.sovan92.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(generator = "custom-step-generator")
    @GenericGenerator(
            name = "custom-step-generator",
            strategy = "com.tutorial.sovan92.generator.CustomStepIdGenerator"
    )
    private Double id;
    private String name;
    private String email;

}
