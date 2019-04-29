package com.zopa.quote.service;

import com.zopa.quote.model.Lender;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class CSVReaderTest {

    private String filePath;

    @Before
    public void setUp() {
        filePath = getClass().getClassLoader().getResource("market.csv").getPath();
    }

    @Test
    public void readCSVFile() {

        CSVReader csvReader = new CSVReader();
        List<Lender> lenderList = csvReader.readCSVFile(filePath);
        assertEquals(lenderList.get(0).getName(), "Bob");
    }

}