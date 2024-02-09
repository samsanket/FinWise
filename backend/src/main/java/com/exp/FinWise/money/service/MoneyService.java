package com.exp.FinWise.money.service;

import com.exp.FinWise.money.dto.ExpenseDTO;
import com.exp.FinWise.money.dto.MoneyDto;
import com.exp.FinWise.money.model.Expense;
import com.exp.FinWise.money.model.ExpenseCategory;
import com.exp.FinWise.money.repository.ExpenseRepository;
import com.exp.FinWise.money.repository.MoneyRepository;
import com.exp.FinWise.money.response.GetMoneyForCurrentDateResponse;
import com.exp.FinWise.money.utility.ObjectConverter;
import com.exp.FinWise.response.ResponseCommon;
import com.exp.FinWise.usersOnBoarding.model.User;
import com.exp.FinWise.usersOnBoarding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.List;

import static java.math.BigDecimal.*;

@Service
public class MoneyService {

    @Autowired
    MoneyRepository moneyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExpenseRepository expenseRepository;


    public GetMoneyForCurrentDateResponse getmoneyForCurrentMonth(UserDetails customUserDetails) {

        User user=userRepository.findByUsername(customUserDetails.getUsername()).get();
        List<MoneyDto> byUserforCurrentMonth = moneyRepository.findByUserforCurrentMonth(user, 2024, 1).stream().map(ObjectConverter::convertMoneyEntityToMoneyDto).toList();
        return new GetMoneyForCurrentDateResponse(HttpStatus.ACCEPTED,byUserforCurrentMonth);
    }

    public ResponseCommon saveExpences(UserDetails userDetails, ExpenseDTO expenseDTO) {

//        private int userID;
//        private String categoryName;
//        private BigDecimal amount;
//        private String description;
//        private LocalDateTime date;

        if ( expenseDTO.getAmount().compareTo(ZERO) == 0  || expenseDTO.getAmount().equals(0.00) || expenseDTO.getAmount().equals(0)){
            throw new RuntimeException("Amount is ZERO");
        }
        if (expenseDTO.getDate().isEqual(null) || expenseDTO.getDescription().isEmpty()){
            throw  new RuntimeException("Description is null");
        }

        Expense expense = new Expense();
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        expense.setDescription(expenseDTO.getDescription());

        ExpenseCategory expenseCategory= new ExpenseCategory();
        expenseCategory.setCategoryName(expenseDTO.getCategoryName());

        expense.setCategory(expenseCategory);
        expenseRepository.save(expense);

        return new ResponseCommon();

    }
}
