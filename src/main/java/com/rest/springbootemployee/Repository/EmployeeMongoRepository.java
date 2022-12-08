package com.rest.springbootemployee.Repository;

import com.rest.springbootemployee.Models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMongoRepository extends MongoRepository<Employee, String> {


}
