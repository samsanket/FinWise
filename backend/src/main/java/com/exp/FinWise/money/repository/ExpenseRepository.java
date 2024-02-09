package com.exp.FinWise.money.repository;

import com.exp.FinWise.money.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
