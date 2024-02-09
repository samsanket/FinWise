package com.exp.FinWise.money.response;


import com.exp.FinWise.money.dto.MoneyDto;
import org.springframework.http.HttpStatus;


import java.util.List;

public class GetMoneyForCurrentDateResponse {



    HttpStatus httpStatus;

    List<MoneyDto> money;



    public GetMoneyForCurrentDateResponse(org.springframework.http.HttpStatus httpStatus, List<MoneyDto> moneyDtoList) {
    this.httpStatus=httpStatus;
    this.money=moneyDtoList;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<MoneyDto> getMoney() {
        return money;
    }

    public void setMoney(List<MoneyDto> money) {
        this.money = money;
    }
}
