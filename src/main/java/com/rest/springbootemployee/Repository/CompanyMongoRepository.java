package com.rest.springbootemployee.Repository;

import com.rest.springbootemployee.Models.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMongoRepository extends MongoRepository<Company,String> {
}
