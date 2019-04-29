package com.zopa.quote.service;

import com.zopa.quote.constants.AppConstants;
import com.zopa.quote.model.Lender;
import com.zopa.quote.model.Quote;
import com.zopa.quote.validator.RequestAmountValidator;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class responsible for processing the csv file and return
 * best quote based on the interest rate
 */
public class QuoteProcessor {

    /**
     * Method to process the csv file and evaluate for best offers among the list
     * of offers and return the quote with total repayment amount
     * @param filePath file path
     * @param loanAmount loan amount requested
     * @return quote
     */
    public Quote processQuotes(String filePath, String loanAmount) {
        CSVReader csvReader = new CSVReader();
        // read the csv file and fetch all the interest rates
        List<Lender> lenderList = csvReader.readCSVFile(filePath);
        //validate the request amount
        new RequestAmountValidator().validate(loanAmount,lenderList);
        // evaluate list of best offers among the total lenders
        List<Lender> availableOffers = new LenderEvaluator().getAvailableOffers(lenderList, Integer.parseInt(loanAmount));
        //calculate the interest rates and return the best quote
        return getQuote(availableOffers, Integer.parseInt(loanAmount));
    }

    /**
     * Calculate the interest rate based on the available offers
     * @param availableOffers list of filtered orders
     * @param loanAmount loan amount
     * @return quote
     */
    private Quote getQuote(List<Lender> availableOffers, int loanAmount) {
        BigDecimal monthlyInterest = new BigDecimal(0);
        Quote quote = new Quote();
        InterestCalculator interestCalculator = new InterestCalculator();
        // calculate for monthly interest for east of the filtered offers
        for (Lender lender : availableOffers) {
            monthlyInterest = monthlyInterest.add(interestCalculator.getMonthlyRepayment(lender.getAvailableAmount(),
                    lender.getRate(), AppConstants.TENURE));
        }
        quote.setRequestedAmount(new BigDecimal(loanAmount));
        quote.setMonthlyRepayment(monthlyInterest);
        quote.setRate(interestCalculator.getAvgInterestRate(availableOffers, loanAmount));
        quote.setTotalRepayment(interestCalculator.getYearlyRepayment(monthlyInterest, AppConstants.TENURE));
        return quote;
    }

}
