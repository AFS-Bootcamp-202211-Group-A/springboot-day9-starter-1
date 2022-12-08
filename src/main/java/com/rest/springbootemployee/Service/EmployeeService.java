package com.rest.springbootemployee.Service;

import com.rest.springbootemployee.Models.Employee;
import com.rest.springbootemployee.Repository.EmployeeMongoRepository;
import com.rest.springbootemployee.Repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {// SUT

    private EmployeeRepository employeeRepository; // DOC
    private EmployeeMongoRepository employeeMongoRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMongoRepository employeeMongoRepository) {
        this.employeeMongoRepository = employeeMongoRepository;
        this.employeeRepository = employeeRepository;
    }

    // 1. verify interaction
        // when EmployeeService.findAll is called, it will call employeeRepository.findAll()
    // 2. verify data
        // return the data get from employeeRepository.findAll() without any change.
    public List<Employee> findAll() {
        return employeeMongoRepository.findAll();
    }

    // 1. verify interaction
        // when EmployeeService.update is called, it will call employeeRepository.findById(id)
    // 2. verify data
        // when input an employee, only the age and salary will be changed, name and gender will not change.
    public Employee update(String id, Employee employee) {
        Employee existingEmployee = this.findById(id);
        if (employee.getAge() != null) {
            existingEmployee.setAge(employee.getAge());
        }
        if (employee.getSalary() != null) {
            existingEmployee.setSalary(employee.getSalary());
        }
        employeeMongoRepository.save(existingEmployee);
        return existingEmployee;
    }


    public Employee findById(String id) {
        return employeeMongoRepository.findById(id).get();
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public List<Employee> findByPage(int page, int pageSize) {
        PageRequest pageable = PageRequest.of(page-1, pageSize);
        return employeeMongoRepository.findAll(pageable).toList();
    }

    public void delete(String id) {
        employeeMongoRepository.deleteById(id);
    }

    public Employee create(Employee employee) {
        return employeeMongoRepository.save(employee);
    }
}
