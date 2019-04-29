package com.zopa.quote.validator;

import com.zopa.quote.Exception.QuoteException;
import com.zopa.quote.model.Lender;
import com.zopa.quote.service.CSVReader;
import com.zopa.quote.validator.RequestAmountValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

public class RequestAmountValidatorTest {

    private List<Lender> lenders ;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Before
    public void setUp() {

        CSVReader csvReader = new CSVReader();
        lenders= csvReader.readCSVFile(getClass().getClassLoader().getResource("market.csv").getPath());
    }

    @Test
    public void validateInvalidIntegers() {
        exceptionRule.expect(QuoteException.class);
        exceptionRule.expectMessage("Requested amount is not a valid number");
        new RequestAmountValidator().validate("100cer",lenders);
    }

    @Test
    public void validateInvalidRangeOfRequestAmount() {
        exceptionRule.expect(QuoteException.class);
        exceptionRule.expectMessage("requested loan should be of any £100 increment between " +
                "£1000 and £15000");
        new RequestAmountValidator().validate("1040",lenders);
    }

    @Test
    public void validateInvalidRangeOfRequestAmountRange() {
        exceptionRule.expect(QuoteException.class);
        exceptionRule.expectMessage("market does not have sufficient offers from " +
                "lenders to satisfy the loan");
        new RequestAmountValidator().validate("2400",lenders);
    }
}