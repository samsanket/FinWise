package com.exp.FinWise.money.utility;

import com.exp.FinWise.money.dto.ExpenseDTO;
import com.exp.FinWise.money.dto.MoneyDto;
import com.exp.FinWise.money.model.Expense;
import com.exp.FinWise.money.model.Money;
import org.springframework.beans.BeanUtils;

public class ExpencesObjectCovertor {


    public static ExpenseDTO convertExpencesEntityToExpenxesDto(Expense expense) {
        ExpenseDTO expenseDTO = new ExpenseDTO();

        BeanUtils.copyProperties(expense, expenseDTO);
        return expenseDTO;

    }



    public Expense  convertMoneyDtoToMoneyEntity(ExpenseDTO  expenseDTO){
        Expense expense= new Expense();
        BeanUtils.copyProperties(expenseDTO,expense);
        return expense;
    }

}
