package com.rafaelamaral.dsmeta.services;

import com.rafaelamaral.dsmeta.dto.SaleDTO;
import com.rafaelamaral.dsmeta.entities.Sale;
import com.rafaelamaral.dsmeta.repositories.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository){
        this.saleRepository = saleRepository;
    }

    public Page<SaleDTO> findSales(String minDate , String maxDate , Pageable pageable){

        var today = LocalDate.ofInstant(Instant.now() , ZoneId.systemDefault());

        var min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        var max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        Page<Sale> result = saleRepository.findSales(min , max , pageable);
        return result.map(x -> new SaleDTO(x));
    }
}
