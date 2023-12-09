package com.example.company.service;

import com.example.company.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    String deleteEmployee(Long employee_no);
    Employee addEmployee(Employee employee);
    Optional<Employee> changeEmployee(Long employee_no, Employee newEmployee);
}
