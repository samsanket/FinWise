package com.exp.FinWise.money.response;

import com.exp.FinWise.money.dto.ExpenseDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public class GetExpencesForCurrentDateResponse {

    HttpStatus httpStatus;

    List<ExpenseDTO> expenseDTOList;

    public GetExpencesForCurrentDateResponse() {
    }

    public GetExpencesForCurrentDateResponse(HttpStatus httpStatus, List<ExpenseDTO> expenseDTOList) {
        this.httpStatus = httpStatus;
        this.expenseDTOList = expenseDTOList;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<ExpenseDTO> getExpenseDTOList() {
        return expenseDTOList;
    }

    public void setExpenseDTOList(List<ExpenseDTO> expenseDTOList) {
        this.expenseDTOList = expenseDTOList;
    }
}
