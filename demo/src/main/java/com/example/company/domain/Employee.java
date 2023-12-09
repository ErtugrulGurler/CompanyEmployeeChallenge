package com.example.company.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity @Table
public class Employee {

    public Employee() {
    }
    public Employee(Long id, String employee_name, Company company) {
        this.id = id;
        this.employee_name = employee_name;
        this.company = company;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String employee_name;


    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
