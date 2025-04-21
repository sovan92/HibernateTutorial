package com.tutorial.sovan92.entities;

import jakarta.persistence.*;
import lombok.*;
/*

Understanding table strategy

 */


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE , generator = "employee_gen")
    @TableGenerator( name = "employee_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", initialValue = 1000, allocationSize = 100)
    private Long id;
    private String name;

}
