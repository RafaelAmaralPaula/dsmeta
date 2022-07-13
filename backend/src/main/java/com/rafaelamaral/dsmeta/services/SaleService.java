package com.rafaelamaral.dsmeta.services;

import com.rafaelamaral.dsmeta.dto.SaleDTO;
import com.rafaelamaral.dsmeta.entities.Sale;
import com.rafaelamaral.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;


    public Page<SaleDTO> findSales(String minDate , String maxDate , Pageable pageable){

        LocalDate today = LocalDate.ofInstant(Instant.now() , ZoneId.systemDefault());

        LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        Page<Sale> result = saleRepository.findSales(min , max , pageable);
        return result.map(x -> new SaleDTO(x));
    }


}
