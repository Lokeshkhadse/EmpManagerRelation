package com.example.EmpRoleManager.Entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RoleMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Rolemap_id;

    @OneToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private  Role role;
}
