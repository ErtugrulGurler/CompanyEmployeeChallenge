package com.example.company.service;

import com.example.company.domain.Employee;
import com.example.company.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class EmployeeServiceImp implements EmployeeService{
    private final EmployeeRepo employeeRepo;
    @Autowired
    public EmployeeServiceImp(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }
    @Override
    public String deleteEmployee(Long employee_no) {
        Optional<Employee> employeeToDelete = Optional.ofNullable(employeeRepo.findById(employee_no)
                .orElseThrow(() -> new RuntimeException("Couldn't find a employee to delete.")));
        employeeRepo.deleteById(employee_no);
        return employeeToDelete.get().getEmployee_name() + " is deleted.";}
    @Override
    public Employee addEmployee(Employee employee) {
        employeeRepo.save(employee);
        return employee;
    }
    @Override
    public Optional<Employee> changeEmployee(Long employee_no, Employee newEmployee) {
        Employee oldEmployee = employeeRepo.findById(employee_no).orElseThrow(()-> new RuntimeException("Company not found. "));
        if (!Objects.equals(oldEmployee.getId(), newEmployee.getId())){
            throw new RuntimeException("Employee IDs must be the same.");
        }
        if (newEmployee.getEmployee_name() != null) {
            oldEmployee.setEmployee_name(newEmployee.getEmployee_name());
        }else throw new RuntimeException("Enter company name.");
        oldEmployee.setCompany(newEmployee.getCompany());
        employeeRepo.save(oldEmployee);
        return employeeRepo.findById(oldEmployee.getId());
    }
}
