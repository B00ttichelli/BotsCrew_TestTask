package com.example.botscrew_testtask.domain;

import com.example.botscrew_testtask.enums.Degree;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;
    private String name;

    private String lastName;
    @ToString.Exclude
    private Degree degree;
    @ToString.Exclude
    private BigDecimal salary;
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "lectorsOfDepartment")

    @ToString.Exclude
    private Set<Department> departments;


}
