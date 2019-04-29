package com.zopa.quote.validator;

import com.zopa.quote.Exception.QuoteException;
import com.zopa.quote.constants.AppConstants;
import com.zopa.quote.model.Lender;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class responsible for validating request amount
 */
public class RequestAmountValidator {

    /**
     * Method to throw exception if loan amount is not integer or
     * if the value is not between pre-defined range
     * @param loanAmount loan amount
     * @return true if validated
     */
    public boolean validate(String loanAmount, List<Lender> lenders){

        if(!loanAmount.matches("-?\\d+"))
           throw new QuoteException("Requested amount is not a valid number");
        int loanAmt = Integer.parseInt(loanAmount);
        if(loanAmt < AppConstants.LOWER_BOUND || loanAmt > AppConstants.UPPER_BOUND
                || (loanAmt%AppConstants.INCREMENT_VALUE!=0))
            throw  new QuoteException("requested loan should be of any £100 increment between " +
                    "£1000 and £15000");

        if(loanAmt>lenders.parallelStream().map(s -> s.getAvailableAmount()).reduce(BigDecimal::add).get().intValue())
            throw  new QuoteException("market does not have sufficient offers from " +
                    "lenders to satisfy the loan");
        return true;
    }
}
