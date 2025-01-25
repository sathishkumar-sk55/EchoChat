package com.frost.echochat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import com.frost.echochat.model.ChatMessage;

@Slf4j
@Controller
public class ChatController {

    @MessageMapping("chat.sendMessage/{roomId}")
    @SendTo("/topic/public/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable String roomId, @Payload ChatMessage message){
        log.info("Room : {} message send by User : {} -->  {}",roomId ,message.getSender(),message.getContent());
        return message;
    }

    @MessageMapping("chat.addUser/{roomId}")
    @SendTo("/topic/public/{roomId}")
    public ChatMessage addUser(@DestinationVariable String roomId, @Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor){
        log.info("Room : {} user joined : {}",roomId ,message.getSender());
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }
}
