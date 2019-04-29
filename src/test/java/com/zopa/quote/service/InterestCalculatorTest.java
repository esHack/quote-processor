package com.zopa.quote.service;

import com.zopa.quote.model.Lender;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class InterestCalculatorTest {

    private InterestCalculator interestCalculator ;
    private List<Lender> lenders ;

    @Before
    public void setUp() {
        interestCalculator = new InterestCalculator();
        CSVReader csvReader = new CSVReader();
        lenders= csvReader.readCSVFile(getClass().getClassLoader().getResource("market.csv").getPath());
    }

    @Test
    public void getMonthlyRepayment() {
        BigDecimal monthlyRepayment= interestCalculator.getMonthlyRepayment(new BigDecimal(1000),new BigDecimal(0.07),36);
        assertEquals(monthlyRepayment.intValue(),30);
    }

    @Test
    public void getYearlyRepayment() {
        BigDecimal yearlyRepayment= interestCalculator.getYearlyRepayment(new BigDecimal(100),36);
        assertEquals(yearlyRepayment.intValue(),3600);
    }

    @Test
    public void getAvgInterestRate() {
        BigDecimal avgInterestRate= interestCalculator.getAvgInterestRate(lenders.subList(0,3),1000);
        assertEquals(avgInterestRate.doubleValue(),11.8,0);
    }
}