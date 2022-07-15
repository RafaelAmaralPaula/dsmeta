package com.rafaelamaral.dsmeta.services;

import com.rafaelamaral.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    private final SaleRepository saleRepository;

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    public SmsService(SaleRepository saleRepository){
        this.saleRepository = saleRepository;
    }

    public void sendSms(Long id){
        var sale = saleRepository.findById(id).get();

        var date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();

        var msg = "O vendedor " + sale.getSellerName() + " foi destaque em " + date
                + " com um total de R$ " + String.format("%.0f" , sale.getAmount());

        Twilio.init(twilioSid , twilioKey);

        PhoneNumber phoneNumberTo = new PhoneNumber(twilioPhoneTo);
        PhoneNumber phoneNumberFrom = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(phoneNumberTo , phoneNumberFrom , msg).create();

        logger.info("SEND MESSAGE WITH " + message.getSid());

    }

}
