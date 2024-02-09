package com.exp.FinWise.money.repository;

import com.exp.FinWise.money.model.Money;
import com.exp.FinWise.usersOnBoarding.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface MoneyRepository extends JpaRepository<Money,Long> {
    @Query("SELECT m FROM Money m " +
            "WHERE m.user = :user " +
            "AND MONTH(m.dateTime) = :month " +
            "AND YEAR(m.dateTime) = :year")
    public List<Money> findByUserforCurrentMonth(@Param("user") User user, @Param("year") int year, @Param("month") int month);

}

