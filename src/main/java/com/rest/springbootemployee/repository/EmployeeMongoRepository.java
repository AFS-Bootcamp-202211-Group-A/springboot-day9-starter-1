package com.rest.springbootemployee.repository;

import com.rest.springbootemployee.entity.Employee;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface EmployeeMongoRepository extends MongoRepository<Employee, String> {
//    @Query("{'gender' : ?0}") <- turns out don't need this is ok too
    List<Employee> findByGender(String gender);

    @Aggregation(pipeline = {
            "{ '$skip' : ?0 }",
            "{ '$limit' : ?1 }"
    })
    List<Employee> findByPage(int skipTo, int pageSize);
}
