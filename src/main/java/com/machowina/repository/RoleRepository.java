package com.machowina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.machowina.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
