package com.outdoors.hobbies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebSocketController {
	private final SimpMessagingTemplate template;

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }
    
    // TODO: add username to the message (can get it as pathVariable?)
    @MessageMapping("/send/message")
    public void onReceivedMesage(String message){
        this.template.convertAndSend("/chat", "[" + new SimpleDateFormat("HH:mm:ss").format(new Date())+"]" + ":"+message);
    }

}
