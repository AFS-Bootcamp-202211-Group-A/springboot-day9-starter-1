package com.rest.springbootemployee.Repository;

import com.rest.springbootemployee.Models.Company;
import com.rest.springbootemployee.Models.Employee;
import com.rest.springbootemployee.Exceptions.NoCompanyFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        ArrayList<Employee> employeesOfCompany1 = new ArrayList<>();
        employeesOfCompany1.add(new Employee("Carlos", 26, "Male", 70000));
        employeesOfCompany1.add(new Employee("Nicole", 22, "Female", 80000));
        companies.add(new Company("spring", employeesOfCompany1));

        ArrayList<Employee> employeesOfCompany2 = new ArrayList<>();
        employeesOfCompany2.add(new Employee("Alice", 21, "Female", 90000));
        employeesOfCompany2.add(new Employee("Bob", 20, "Male", 80000));
        companies.add(new Company("summer", employeesOfCompany2));

        ArrayList<Employee> employeesOfCompany3 = new ArrayList<>();
        employeesOfCompany3.add(new Employee("Zoe", 23, "Female", 85000));
        employeesOfCompany3.add(new Employee("Thomas", 22, "Male", 83000));
        companies.add(new Company("autumn", employeesOfCompany3));
    }

    public List<Company> findAll() {
        return companies;
    }

    public List<Company> findByPage(int page, int pageSize) {
        return companies.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Company findById(Integer id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(NoCompanyFoundException::new);
    }

    public void delete(Integer id) {
        Company existingCompany = findById(id);
        companies.remove(existingCompany);
    }

    public List<Employee> getEmployees(Integer id) {
        return findById(id).getEmployees();
    }

    public Company create(Company company) {
        Integer nextId = generateNextId();
        company.setId(nextId);
        companies.add(company);
        return company;
    }

    private Integer generateNextId() {
        int maxId = companies.stream()
                .mapToInt(Company::getId)
                .max()
                .orElse(1);
        return maxId + 1;
    }

    public Company update(Integer id, Company company) {
        Company existingCompany = findById(id);
        if (company.getName() != null) {
            existingCompany.setName(company.getName());
        }
        return existingCompany;
    }

    public void clearAll() {
        companies.clear();
    }
}