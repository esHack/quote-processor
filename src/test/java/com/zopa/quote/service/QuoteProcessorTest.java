package com.zopa.quote.service;

import org.junit.Before;
import org.junit.Test;

public class QuoteProcessorTest {

    private String filePath;

    @Before
    public void setUp() {
        filePath = getClass().getClassLoader().getResource("market.csv").getPath();
    }
    @Test
    public void processQuotes() {
        QuoteProcessor quoteProcessor = new QuoteProcessor();
        quoteProcessor.processQuotes(filePath,"1000");
    }
}