package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    
    public Message insertMessage(Message message){
        //message text not blank, <256 chars, and postedBy is a real user
        Message findMessage = messageRepository.findById(message.getMessageId().longValue()).orElse(null);
        if(message.getMessageId() != null 
        || message.getMessageText().isEmpty() 
        || message.getMessageText().length() > 255
        || findMessage == null){
            return null;
        }
        return messageRepository.save(message);
    }
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
    public Message getMessageById(Integer messageId){
        return messageRepository.findById(messageId.longValue()).orElse(null);
    }
    public Message updateMessage(Integer messageId, String messageText){
        Message findMessage = messageRepository.findById(messageId.longValue()).orElse(null);
        if(findMessage == null || messageText.isEmpty() || messageText.length() > 255){
            return null; //400
        }
        findMessage.setMessageText(messageText);
        messageRepository.save(findMessage);
        return findMessage;
    }
    public Message deleteMessage(Message message){
        Message findMessage = messageRepository.findById(message.getMessageId().longValue()).orElse(null);
        if(findMessage != null){
            messageRepository.delete(message);
            return findMessage;
        }
        return null;
    }
    public List<Message> getAllMessagesByAccountId(Integer postedBy){
        return messageRepository.findMessagesByPostedBy(postedBy);
    }
}
