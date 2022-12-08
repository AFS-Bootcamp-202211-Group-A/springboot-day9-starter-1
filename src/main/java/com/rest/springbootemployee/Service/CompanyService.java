package com.rest.springbootemployee.Service;

import com.rest.springbootemployee.Exceptions.NoCompanyFoundException;
import com.rest.springbootemployee.Exceptions.NoEmployeeFoundException;
import com.rest.springbootemployee.Models.Company;
import com.rest.springbootemployee.Repository.CompanyMongoRepository;
import com.rest.springbootemployee.Models.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyMongoRepository companyMongoRepository;

    public CompanyService( CompanyMongoRepository companyMongoRepository) {
        this.companyMongoRepository = companyMongoRepository;
    }

    public List<Company> findAll() {
        return companyMongoRepository.findAll();
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        PageRequest pageable = PageRequest.of(page-1, pageSize);
        return companyMongoRepository.findAll(pageable).toList();
    }

    public Company findById(String companyId) throws NoCompanyFoundException {
        return companyMongoRepository.findById(companyId).orElseThrow(NoCompanyFoundException::new);
    }

    public Company create(Company company) {
        return companyMongoRepository.save(company);
    }

    public void delete(String companyId) {
        companyMongoRepository.deleteById(companyId);
    }

    public Company update(String companyId, Company toUpdateCompany) {
        Company existingCompany = this.findById(companyId);
        if (toUpdateCompany.getName() != null) {
            existingCompany.setName(toUpdateCompany.getName());
        }
        companyMongoRepository.save(existingCompany);
        return existingCompany;
    }

    public List<Employee> getEmployees(String companyId) {
        Company company = this.findById(companyId);
        return company.getEmployees();
    }

}
