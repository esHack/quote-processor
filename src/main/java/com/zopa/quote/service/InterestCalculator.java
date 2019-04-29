package com.zopa.quote.service;

import com.zopa.quote.model.Lender;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Class responsible for interest rate, monthly and yearly amounts
 */
public class InterestCalculator {

    /**
     *Method to calculate the monthly payment based on the below formulae
     *    c = (Pr / 1 - ((1+r)^n)^-1) // n = tenure, r = rate
     * @param principle total loan amount
     * @param rate interest rate
     * @param tenure no of months
     * @return interest rate
     */
    public BigDecimal getMonthlyRepayment(BigDecimal principle, BigDecimal rate, int tenure) {
        BigDecimal monthlyInterestRate = rate.divide(new BigDecimal(12), 6, BigDecimal.ROUND_HALF_UP);
        BigDecimal pr = principle.multiply(monthlyInterestRate);
        BigDecimal incrementRate = BigDecimal.ONE.add(monthlyInterestRate);
        Double onePlusRPowN = Math.pow(incrementRate.doubleValue(), (new BigDecimal(-tenure)).doubleValue());
        return pr.divide(BigDecimal.ONE.subtract(new BigDecimal(onePlusRPowN)),2, BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * Calculate yearly repayment
     * @param amount loan amount
     * @param months months
     * @return yearly repayment
     */
    public BigDecimal getYearlyRepayment(BigDecimal amount, int months) {
        return amount.multiply(new BigDecimal(months));
    }

    /**
     * Method th evaluate avg interest rate
     * @param lenderList  filtered lenders
     * @param loanAmount loan amount
     * @return
     */
    public BigDecimal getAvgInterestRate(List<Lender> lenderList, int loanAmount) {
        BigDecimal interestRate = new BigDecimal(0);
        // iterate through filtered lenders and calculate the arithmetic mean
        for (Lender lender: lenderList){
            interestRate=interestRate.add((lender.getAvailableAmount().divide(new BigDecimal(loanAmount),2, RoundingMode.HALF_UP))
                            .multiply(lender.getRate()));
        }
        return interestRate.multiply(new BigDecimal(100)).setScale(1, RoundingMode.HALF_DOWN);
    }
}
