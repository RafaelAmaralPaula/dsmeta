package com.rafaelamaral.dsmeta.dto;

import com.rafaelamaral.dsmeta.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleDTO{

    private Long id;

    private String sellerName;

    private Integer visited;

    private Integer deals;

    private Double amount;

    private LocalDate date;

    public SaleDTO(Sale sale){
        id = sale.getId();
        sellerName = sale.getSellerName();
        visited = sale.getVisited();
        deals = sale.getDeals();
        amount = sale.getAmount();
        date = sale.getDate();
    }

}
