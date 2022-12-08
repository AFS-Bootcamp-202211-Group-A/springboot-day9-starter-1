package com.rest.springbootemployee.Service;

import com.rest.springbootemployee.Models.Company;
import com.rest.springbootemployee.Repository.CompanyMongoRepository;
import com.rest.springbootemployee.Models.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyMongoRepository companyMongoRepository;

    public CompanyService(CompanyMongoRepository companyMongoRepository) {
        this.companyMongoRepository = companyMongoRepository;
    }

    public List<Company> findAll() {
        return companyMongoRepository.findAll();
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        PageRequest pageable = PageRequest.of(page-1, pageSize);
        return companyMongoRepository.findAll(pageable).toList();
    }

    public Company findById(String companyId) {
        return companyMongoRepository.findById(companyId).get();
    }

    public Company create(Company company) {
        return companyMongoRepository.save(company);
    }

    public void delete(String companyId) {
        companyMongoRepository.deleteById(companyId);
    }

    public Company update(String companyId, Company toUpdateCompany) {
        Company existingCompany = companyMongoRepository.findById(companyId).get();
        if (toUpdateCompany.getName() != null) {
            existingCompany.setName(toUpdateCompany.getName());
        }
        companyMongoRepository.save(existingCompany);
        return existingCompany;
    }

    public List<Employee> getEmployees(String companyId) {
        Company company = companyMongoRepository.findById(companyId).get();
        return company.getEmployees();
    }

}
