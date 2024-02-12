package com.exp.FinWise.money.controller;

import com.exp.FinWise.annotation.CurrentUser;
import com.exp.FinWise.money.dto.ExpenseDTO;
import com.exp.FinWise.money.response.GetMoneyForCurrentDateResponse;
import com.exp.FinWise.money.service.MoneyService;
import com.exp.FinWise.response.ResponseCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/money")
public class MoneyController {

    @Autowired
    MoneyService moneyService;

    @GetMapping("/month")
    public GetMoneyForCurrentDateResponse getmoneyForCurrentMonth(@CurrentUser UserDetails customUserDetails){
        return moneyService.getmoneyForCurrentMonth(customUserDetails);
    }

    @PostMapping("/save")
    public ResponseCommon addExpeneces(@CurrentUser UserDetails userDetails,
                                       @RequestBody ExpenseDTO expenseDTO){
            return moneyService.saveExpences(userDetails,expenseDTO);
    }

    @GetMapping("/months")
    public GetMoneyForCurrentDateResponse getexpencesFormonth(@CurrentUser UserDetails customUserDetails){
        return moneyService.getmoneyForCurrentMonth(customUserDetails);
    }
}
