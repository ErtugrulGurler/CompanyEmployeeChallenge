package com.example.company.service;

import com.example.company.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    String deleteCompany(Long company_no);
    Company addCompany(Company company);
    Optional<Company> changeCompany(Long company_no, Company newCompany);
}
