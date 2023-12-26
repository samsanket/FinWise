package com.exp.FinWise.Resume.repository;


import com.exp.FinWise.Resume.model.RequirementModel;
import com.exp.FinWise.usersOnBoarding.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequirementRepository extends JpaRepository<RequirementModel, Long> {

   List<RequirementModel> findAllByUser(User user);
}
