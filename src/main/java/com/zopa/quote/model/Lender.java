package com.zopa.quote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lender {
    private String name;
    private BigDecimal rate;
    private BigDecimal availableAmount;
}
