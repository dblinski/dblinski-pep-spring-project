package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>{

    /*
     * public Message insertMessage(Message message);
     * public List<Message> getAllMessages();
     * public Message getMessageById(int message_id)
     * public boolean deleteMessageById(int message_id)
     * public boolean updateMessageById(int message_id, String new_message_text)
     * public List<Message> getAllMessagesByAccountId(int account_id)
     */

     List<Message> findMessagesByPostedBy(Integer postedBy);
    
}
