package com.example.EmpRoleManager.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.processing.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee {

    @Id
    private int emp_id;

    private String name;

    private String phno;

    private String mail;



}
