package com.zopa.quote;

import com.zopa.quote.model.Quote;
import com.zopa.quote.service.QuoteProcessor;

// Driver program
public class App {

    // Uncomment these to run from IDE
    public static void main(String[] args) {

        if(args[0].isEmpty() || args[1].isEmpty()) {
            System.out.println("Incorrect input format");
            System.out.println("java -jar quote-processor.jar C:\\Users\\iames\\OneDrive\\Desktop\\market.csv 1000");
        }


        QuoteProcessor quoteProcessor = new QuoteProcessor();
        //String path = App.class.getClassLoader().getResource("market.csv").getPath();
        //String path = Paths.get(args[0]).toAbsolutePath();
        //Quote quote =quoteProcessor.processQuotes(path,"1100");
        Quote quote =quoteProcessor.processQuotes(args[0],args[1]);
        System.out.println(quote);
    }
}
