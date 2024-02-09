package com.exp.FinWise.money.utility;

import com.exp.FinWise.money.dto.MoneyDto;
import com.exp.FinWise.money.model.Money;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ObjectConverter {
    public static MoneyDto convertMoneyEntityToMoneyDto(Money money) {
        MoneyDto moneyDto = new MoneyDto();
        BeanUtils.copyProperties(money, moneyDto);
        return moneyDto;

    }


    public Money convertMoneyDtoToMoneyEntity(MoneyDto moneyDto){
        Money money= new Money();
        BeanUtils.copyProperties(moneyDto,money);
        return money;
    }

}
