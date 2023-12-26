package com.exp.FinWise.usersOnBoarding.repository;


import com.exp.FinWise.usersOnBoarding.model.ERole;
import com.exp.FinWise.usersOnBoarding.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
