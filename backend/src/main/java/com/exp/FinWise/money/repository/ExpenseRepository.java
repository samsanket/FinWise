package com.exp.FinWise.money.repository;

import com.exp.FinWise.money.model.Expense;
import com.exp.FinWise.money.model.Money;
import com.exp.FinWise.usersOnBoarding.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {



    @Query("SELECT m FROM Expense m " +
            "WHERE m.user = :user " +
            "AND MONTH(m.dateTime) = :month " +
            "AND YEAR(m.dateTime) = :year")
    List<Expense> findByUserforCurrentMonth(@Param("user") User user, @Param("year") int year, @Param("month") int month);

}
