package com.example.company.service;
import com.example.company.domain.Company;
import com.example.company.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class CompanyServiceImp implements CompanyService {
    private final CompanyRepo companyRepo;
    @Autowired
    public CompanyServiceImp(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();}
    @Override
    public String deleteCompany(Long company_no) {
        Optional<Company> companyToDelete = Optional.ofNullable(companyRepo.findById(company_no)
                .orElseThrow(() -> new RuntimeException("Couldn't find a company to delete.")));
        companyRepo.deleteById(company_no);
        return companyToDelete.get().getCompany_name() + " is deleted.";}
    @Override
    public Company addCompany(Company company) {
        companyRepo.save(company);
        return company;}
    @Override
    public Optional<Company> changeCompany(Long company_no, Company newCompany) {
        Company oldCompany = companyRepo.findById(company_no).orElseThrow(()-> new RuntimeException("Company not found. "));
        if (!Objects.equals(oldCompany.getId(), newCompany.getId())){
            throw new RuntimeException("Company IDs must be the same.");
        }
        if (newCompany.getCompany_name() != null) {
            oldCompany.setCompany_name(newCompany.getCompany_name());
        }else throw new RuntimeException("Enter company name.");
        oldCompany.setEmployees(newCompany.getEmployees());
        companyRepo.save(oldCompany);
        return companyRepo.findById(oldCompany.getId());
    }
}
