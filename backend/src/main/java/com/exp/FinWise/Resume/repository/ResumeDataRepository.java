package com.exp.FinWise.Resume.repository;

import com.exp.FinWise.Resume.dto.SearchDataDto;
import com.exp.FinWise.Resume.model.ResumeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeDataRepository extends JpaRepository<ResumeData ,Long> {
    List<ResumeData> findAllByRequirementResponseMapper_MapperId(Long mapperId);

    List<ResumeData> findByEmployeeNameContainingOrEmailContaining(String name,String email);
}