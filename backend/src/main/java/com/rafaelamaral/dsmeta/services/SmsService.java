package com.rafaelamaral.dsmeta.services;

import com.rafaelamaral.dsmeta.entities.Sale;
import com.rafaelamaral.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmsService {
    private static Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    @Autowired
    private SaleRepository saleRepository;

    public void senSms(Long id){
        Sale sale = saleRepository.findById(id).get();

        String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();

        String msg = "O vendedor " + sale.getSellerName() + " foi destaque em " + date
                + " com um total de R$ " + String.format("%.2f" , sale.getAmount());

        Twilio.init(twilioSid , twilioKey);

        PhoneNumber phoneNumberTo = new PhoneNumber(twilioPhoneTo);
        PhoneNumber phoneNumberFrom = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(phoneNumberTo , phoneNumberFrom , msg).create();

        logger.info("SEND MESSAGE WITH " + message.getSid());


    }


}
