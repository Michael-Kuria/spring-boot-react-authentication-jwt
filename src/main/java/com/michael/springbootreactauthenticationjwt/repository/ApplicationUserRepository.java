package com.michael.springbootreactauthenticationjwt.repository;

import com.michael.springbootreactauthenticationjwt.model.ApplicationUser;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends ListCrudRepository<ApplicationUser, String> {
}
