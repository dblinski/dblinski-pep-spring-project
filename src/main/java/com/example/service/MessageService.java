package com.example.service;

import java.util.List;
import java.util.Optional;

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

    public Message persistMessage(Message message, AccountService accountService){
        if(message.getMessageText().isEmpty() 
        || message.getMessageText().length() > 255
        || accountService.getAccountById(message.getPostedBy()) == null){
            return null;
        }
        return messageRepository.save(message);
    }
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
    public Message getMessageById(Integer messageId){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }
        else{
            return null;
        }
    }
    public Message updateMessage(Integer messageId, String messageText){
        Message findMessage = messageRepository.findById(messageId).orElse(null);
        if(findMessage == null || messageText.isEmpty() || messageText.isBlank() || messageText.length() > 255){
            return null; //400
        }
        findMessage.setMessageText(messageText);
        messageRepository.save(findMessage);
        return findMessage;
    }
    public Message deleteMessage(Integer messageId){
        Message findMessage = messageRepository.findById(messageId).orElse(null);
        if(findMessage != null){
            messageRepository.delete(findMessage);
            return findMessage;
        }
        return null;
    }
    public List<Message> getAllMessagesByAccountId(Integer postedBy){
        return messageRepository.findMessagesByPostedBy(postedBy);
    }
}
