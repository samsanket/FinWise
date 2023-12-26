package com.exp.FinWise.Resume.repository;

import com.exp.FinWise.Resume.model.ITKeywordsSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITKeywordsSetRepository extends JpaRepository<ITKeywordsSet ,Long> {

    @Query(value = "select  * from it_keyword where  skills like :skills% or skills like :skill% ;",nativeQuery = true)
    List<ITKeywordsSet> findBySkillsStartingWith(String skills,String skill);

}
