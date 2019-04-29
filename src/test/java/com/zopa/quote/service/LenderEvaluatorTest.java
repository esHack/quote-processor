package com.zopa.quote.service;

import com.zopa.quote.model.Lender;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LenderEvaluatorTest {

    private List<Lender> lenders ;

    @Before
    public void setUp() {

        CSVReader csvReader = new CSVReader();
        lenders= csvReader.readCSVFile(getClass().getClassLoader().getResource("market.csv").getPath());
    }
    @Test
    public void testAvailableOffers() {
        LenderEvaluator lenderEvaluator = new LenderEvaluator();
        List<Lender> availableOffers= lenderEvaluator.getAvailableOffers(lenders,1000);
        availableOffers.forEach(s -> System.out.println(s));
        assertEquals(availableOffers.size(),2);

        List<Lender> availableOffers1= lenderEvaluator.getAvailableOffers(lenders,1100);
        availableOffers1.forEach(s -> System.out.println(s));
        assertEquals(availableOffers1.size(),4);
    }
}