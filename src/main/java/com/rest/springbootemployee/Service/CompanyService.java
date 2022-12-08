package com.rest.springbootemployee.Service;

import com.rest.springbootemployee.Models.Company;
import com.rest.springbootemployee.Repository.CompanyMongoRepository;
import com.rest.springbootemployee.Repository.CompanyRepository;
import com.rest.springbootemployee.Models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private CompanyMongoRepository companyMongoRepository;

    public CompanyService(CompanyRepository companyRepository, CompanyMongoRepository companyMongoRepository) {
        this.companyRepository = companyRepository;
        this.companyMongoRepository = companyMongoRepository;
    }

    public List<Company> findAll() {
        return companyMongoRepository.findAll();
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companyRepository.findByPage(page, pageSize);
    }

    public Company findById(String companyId) {
        return companyMongoRepository.findById(companyId).get();
    }

    public Company create(Company company) {
        return companyRepository.create(company);
    }

    public void delete(String companyId) {
        companyRepository.delete(companyId);
    }

    public Company update(String companyId, Company toUpdateCompany) {
        Company existingCompany = companyRepository.findById(companyId);
        if (toUpdateCompany.getName() != null) {
            existingCompany.setName(toUpdateCompany.getName());
        }
        return existingCompany;
    }

    public List<Employee> getEmployees(String companyId) {
        Company company = companyRepository.findById(companyId);
        return company.getEmployees();
    }

}
