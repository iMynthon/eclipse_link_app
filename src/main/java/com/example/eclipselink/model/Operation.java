package com.example.eclipselink.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "operations")
    @SequenceGenerator(name = "operations",sequenceName = "operations_seq_id",allocationSize = 100)
    private Integer id;

    private double operation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calculated_id")
    private Calculated calculated;
}
