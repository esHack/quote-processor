package com.zopa.quote.service;

import com.zopa.quote.model.Lender;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for fetching the best offers among the various lenders
 *
 */
public class LenderEvaluator {

    /**
     * Method to fetch best available lenders , and make sure the sum is equal to loan amount
     *
     *
     * @param lenders list of lenders
     * @param loanAmount total loan amount
     * @return list of best offers
     */
    public List<Lender> getAvailableOffers(List<Lender> lenders, int loanAmount){
        List<Lender> availableOffers =  new ArrayList();
        //Sort the list to get lowest interest rates first
        lenders.sort((o1, o2) -> o1.getRate().compareTo(o2.getRate()));
        for(Lender lender : lenders){
            if(loanAmount - lender.getAvailableAmount().intValue() >0)
                availableOffers.add(lender);
            else {
                availableOffers.add(new Lender(lender.getName(),lender.getRate(),
                        new BigDecimal(loanAmount)));
                break;
            }

            loanAmount-=lender.getAvailableAmount().intValue();

        }
        return availableOffers;
    }
}
