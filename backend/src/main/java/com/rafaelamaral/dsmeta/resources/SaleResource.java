package com.rafaelamaral.dsmeta.resources;

import com.rafaelamaral.dsmeta.dto.SaleDTO;
import com.rafaelamaral.dsmeta.services.SaleService;
import com.rafaelamaral.dsmeta.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleResource {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SmsService smsService;

    @GetMapping
    public ResponseEntity<Page<SaleDTO>> findAll(
            @RequestParam(name = "minDate" , defaultValue = "") String minDate,
            @RequestParam(name = "maxDate" , defaultValue = "") String maxDate,
            Pageable pageable){
        Page<SaleDTO> page = saleService.findSales(minDate , maxDate , pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}/notification")
    public void notifySms(@PathVariable Long id){
        smsService.senSms(id);
    }
}
