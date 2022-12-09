package com.rest.springbootemployee.Repository;

import com.rest.springbootemployee.Models.Employee;
import com.rest.springbootemployee.Exceptions.NoEmployeeFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    List<Employee> employees;

    public EmployeeRepository() {
        employees = new ArrayList<>();
        employees.add(new Employee("Lily", 20, "Female", 80000));
        employees.add(new Employee("Desiree", 20, "Female", 80000));
        employees.add(new Employee("Rafael", 20, "Male", 80000));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(String id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(NoEmployeeFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee create(Employee employee) {
        String nextId = generateNextId();
        employee.setId(nextId);
        employees.add(employee);
        return employee;
    }

    private String generateNextId() {
        int maxId = employees.stream()
                .mapToInt(employee -> Integer.parseInt(employee.getId()))
                .max()
                .orElse(1) + 1;
        return String.valueOf(maxId);
    }

//    requirement: update age and salary

    public void delete(String id) {
        Employee existingEmployee = findById(id);
        employees.remove(existingEmployee);
    }

    public List<Employee> findByPage(int page, int pageSize) {
        return employees.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public void clearAll() {
        employees.clear();
    }
}

//{
//        "id": 5,
//        "name": "Lily",
//        "age": 20,
//        "gender": "Female",
//        "salary": 8000
//        }
