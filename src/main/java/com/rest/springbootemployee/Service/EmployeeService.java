package com.rest.springbootemployee.Service;

import com.rest.springbootemployee.Exceptions.NoEmployeeFoundException;
import com.rest.springbootemployee.Models.Employee;
import com.rest.springbootemployee.Repository.EmployeeMongoRepository;
import com.rest.springbootemployee.Repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {// SUT

    private EmployeeMongoRepository employeeMongoRepository;

    public EmployeeService( EmployeeMongoRepository employeeMongoRepository) {
        this.employeeMongoRepository = employeeMongoRepository;
    }

    public List<Employee> findAll() {
        return employeeMongoRepository.findAll();
    }

    public Employee update(String id, Employee employee)  throws NoEmployeeFoundException {
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


    public Employee findById(String id) throws NoEmployeeFoundException  {
        return employeeMongoRepository.findById(id).orElseThrow(NoEmployeeFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employeeMongoRepository.findByGender(gender);
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
