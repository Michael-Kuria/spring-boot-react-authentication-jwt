package com.michael.springbootreactauthenticationjwt.repository;

import com.michael.springbootreactauthenticationjwt.model.Student;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ListCrudRepository<Student, String> {
}
