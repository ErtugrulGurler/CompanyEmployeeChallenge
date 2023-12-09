package com.example.company.repository;

import com.example.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {
}
