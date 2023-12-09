package com.example.company.DTOs;

import java.util.List;

public class CompanyDTO {
    private Long id;
    private String companyName;
    private List<EmployeeDTO> employees;


    public CompanyDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    // getter'lar ve setter'lar
}