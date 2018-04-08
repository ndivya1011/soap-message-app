package com.calculator.service;


import java.util.LinkedList;
import java.util.List;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.calculator.dao.GcdDao;
import com.calculator.model.Gcd;
import com.calculator.soap_message_app.GetGcd;
import com.calculator.soap_message_app.GetGcdList;
import com.calculator.soap_message_app.GetInputForGcd;

@Endpoint
public class CalculatorResponseService {
    
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;
    
    @Autowired
    GcdDao gcdDao;

    Long seq;

    @PayloadRoot(namespace = "http://calculator.com/soap-message-app",
            localPart = "getInputForGcd")
    @ResponsePayload
    public GetGcd getInputForGcd(@RequestPayload GetInputForGcd request) {
    	seq = seq +1;
    	GetGcd response = new GetGcd();
    	int ip1 = request.getNum1();
    	int ip2 = request.getNum2();
        while(ip1 != ip2)
        {
            if(ip1 > ip2)
                ip1 -= ip2;
            else
                ip2 -= ip1;
        }
        response.setGcds(ip1);
        jmsTemplate.convertAndSend(queue, String.valueOf(ip1));
        Gcd gcd = new Gcd(seq, ip1);
        gcdDao.insertGcd(gcd);
        return response;
    }
    
    @PayloadRoot(namespace = "http://calculator.com/soap-message-app",
            localPart = "getGcdSum")
    @ResponsePayload	
    public GetGcd getGcdSum() {
    	GetGcd response = new GetGcd();
    	int totalSum =0;
    	List<Gcd> list = gcdDao.getAllGcd();
    	for(Gcd lst : list) {
    		totalSum = totalSum + lst.getGcd();
    	}
    	response.setGcds(totalSum);
        return response;
    }
    
}
