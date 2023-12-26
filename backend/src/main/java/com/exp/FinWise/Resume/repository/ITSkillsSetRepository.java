package com.exp.FinWise.Resume.repository;

import com.exp.FinWise.Resume.model.ITSkillsSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITSkillsSetRepository extends JpaRepository<ITSkillsSet, Long> {

    @Query(value = "select  * from it_skill where  skills like :skills% or skills like :skill% ;",nativeQuery = true)
    List<ITSkillsSet> findBySkillsStartingWith(String skills,String skill);

    List<ITSkillsSet> findAll();

}
