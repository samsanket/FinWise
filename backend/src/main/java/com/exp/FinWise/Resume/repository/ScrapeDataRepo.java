/*
 * Copyright (c) 2023.
 * Sanket Deshpande . All rights reserved
 */

package com.exp.FinWise.Resume.repository;

import com.exp.FinWise.Resume.model.ScrapeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapeDataRepo  extends JpaRepository<ScrapeData,Long> {

    Optional<ScrapeData> findByEmail(String email);
}
