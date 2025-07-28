package com.example.eclipselink.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity(name = "calculated")
public class Calculated {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "calculated_seq")
    @SequenceGenerator(
            name = "calculated_seq",
            sequenceName = "calculated_id_seq",
            allocationSize = 100
    )
    private Integer id;

    private Integer sum;

    private LocalDateTime time;

    @OneToMany(mappedBy = "calculated",cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private List<Operation> orders;
}
