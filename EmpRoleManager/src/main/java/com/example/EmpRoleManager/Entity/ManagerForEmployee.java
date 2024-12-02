package com.example.EmpRoleManager.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ManagerForEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int manEmp_id;

    @ManyToOne
    @JoinColumn(name="Rolemap_id")
    private RoleMapping roleMapping;


    @OneToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

}
