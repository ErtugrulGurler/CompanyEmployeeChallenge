package com.example.company.api;

import com.example.company.DTOs.CompanyDTO;
import com.example.company.DTOs.EmployeeDTO;
import com.example.company.domain.Company;
import com.example.company.domain.Employee;
import com.example.company.service.CompanyService;
import com.example.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Company")
public class CompanyController {
    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {this.companyService = companyService;}
    @GetMapping("/all")
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        List<CompanyDTO> companyDTOs = new ArrayList<>();

        for (Company company : companies) {
            CompanyDTO companyDTO = convertToDTO(company);
            companyDTOs.add(companyDTO);
        }
        return ResponseEntity.ok(companyDTOs);
    }
    @DeleteMapping("{id}")
    public String deleteCompany(@PathVariable("id") Long company_no) {
        return companyService.deleteCompany(company_no);
    }
    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }
    @PutMapping("{id}")
    public Optional<Company> changeCompany(@RequestBody Company company, @PathVariable("id") Long company_no) {
        return companyService.changeCompany(company_no, company);
    }


    private CompanyDTO convertToDTO(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setCompanyName(company.getCompany_name());

        List<Employee> employees = company.getEmployees();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getEmployee_name());

            employeeDTOs.add(employeeDTO);
        }

        companyDTO.setEmployees(employeeDTOs);

        return companyDTO;
    }
}