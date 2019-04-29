package com.zopa.quote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    private BigDecimal requestedAmount;
    private BigDecimal rate;
    private BigDecimal monthlyRepayment;
    private BigDecimal totalRepayment;

    @Override
    public String toString() {
        return "Requested amount:£" + requestedAmount +
                "\nRate:" + rate + "%"+
                "\nMonthly repayment:£" + monthlyRepayment +
                "\nTotal repayment:£" + totalRepayment;
    }
}
