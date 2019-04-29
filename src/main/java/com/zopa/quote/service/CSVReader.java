package com.zopa.quote.service;

import com.zopa.quote.Exception.QuoteException;
import com.zopa.quote.model.Lender;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class responsible for parsing the CSV file and get the list
 * of Lender objects
 *
 */
public class CSVReader {

    /**
     * Method to parse the csv file and return list of Lender objects
     * @param path file path
     * @return lis of lender objects
     */
    public List<Lender> readCSVFile(String path){

        List<Lender> inputList = new ArrayList<>();
        try{
            File inputF = new File(path);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
           throw new QuoteException("Error reading the csv file", e);
        }

        return inputList ;
        }

    /**
     * Function that maps lines from csv to Lender object
      */
    private Function<String, Lender> mapToItem = (line) -> {
        String[] param = line.split(",");// a CSV has comma separated lines
        Lender lender = new Lender();
        lender.setName(param[0]);
        lender.setRate(new BigDecimal(param[1]));
        if (param[2] != null && param[2].trim().length() > 0) {
            lender.setAvailableAmount(new BigDecimal(param[2]));
        }
        return lender;
    };
}
