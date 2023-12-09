package com.example.company.api;

import com.example.company.DTOs.CompanyDTO;
import com.example.company.DTOs.EmployeeDTO;
import com.example.company.domain.Employee;
import com.example.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getEmployees () {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = convertToDTO(employee);
            employeeDTOs.add(employeeDTO);
        }
        return ResponseEntity.ok(employeeDTOs);}
    @DeleteMapping("{id}")
    public String deleteEmployee(@PathVariable("id") Long employee_no) {
        return employeeService.deleteEmployee(employee_no);}
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }
    @PutMapping("{id}")
    public Optional<Employee> changeEmployee(@RequestBody Employee employee, @PathVariable("id") Long employee_no) {
        return employeeService.changeEmployee(employee_no,employee);
    }
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getEmployee_name());

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(employee.getCompany().getId());
        companyDTO.setCompanyName(employee.getCompany().getCompany_name());

        employeeDTO.setCompany(companyDTO);
        return employeeDTO;
    }
}
