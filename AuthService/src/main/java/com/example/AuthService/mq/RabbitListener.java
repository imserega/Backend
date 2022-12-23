package com.example.AuthService.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitListener {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private AmqpTemplate amqpTemplate;

//    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "StudentTokenCheck")
//    public void authentication(String msg) {
//        try{
//
//        }catch (JsonProcessingException e){
//            System.out.println(e);
//        }
//    }

}
