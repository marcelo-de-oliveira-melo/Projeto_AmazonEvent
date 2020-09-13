package com.spring.cevento.repository;

import com.spring.cevento.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,String> {

   Role getByRole(String role);
}
