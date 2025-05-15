package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;

    /**
     * 1. new user registration
     * POST /register
     * if ok, status 200; if unsuccessful because duplicate username, status 409 (conflict); otherwise, status 400 (client error)
     * @param account
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account){
        Account newAccount = accountService.insertAccount(account);
        if(newAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else if(newAccount != null && newAccount.getAccountId() == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else{
            return new ResponseEntity<>(newAccount, HttpStatus.OK);
        }
    }
    /**
     * 2. user login
     * POST /login
     * if successful, status 200; otherwise, status 401 (unauthorized)
     * @param account
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        Account newAccount = accountService.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(newAccount == null){
            return new ResponseEntity<>(newAccount, HttpStatus.UNAUTHORIZED);
        }
        else{
           return new ResponseEntity<>(newAccount, HttpStatus.OK);
        }
    }
    /**
     * 3. create new message
     * POST /messages
     * if successful, status 200; otherwise, status 400 (client error)
     * @param message
     * @return
     */
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message newMessage = messageService.persistMessage(message, accountService);
        if(newMessage == null){
            return new ResponseEntity<>(newMessage, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(newMessage, HttpStatus.OK);
        }
    }
    /**
     * 4. retrieve all messages
     * GET /messages
     * always status 200
     * @return
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> newList = messageService.getAllMessages();
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }
    /**
     * 5. retrieve message by ID
     * GET /messages/{message_id}
     * always status 200
     * 
     * @param message_id
     * @return
     */
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer message_id){
        Message message = messageService.getMessageById(message_id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    /**
     * 6. delete message by message ID
     * DELETE /messages/{message_id}
     * always status 200
     * @param message_id
     * @return
     */
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity deleteMessageById(@PathVariable Integer message_id){
        Message message = messageService.deleteMessage(message_id);
        if(message == null){
            return new ResponseEntity("", HttpStatus.OK);
        }
        else{
            return new ResponseEntity(1, HttpStatus.OK);
        }
    }
    /**
     * 7. update message by message ID
     * PATCH /messages/{message_id}
     * if successful, status 200; otherwise, status 400 (client error)
     * @param messageText
     * @param message_id
     * @return
     */
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity updateMessageById(@RequestBody Message message, @PathVariable Integer message_id){
        Message newMessage = messageService.updateMessage(message_id, message.getMessageText());
        if(newMessage == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(1, HttpStatus.OK);
        }
    }
    /*
     * 8. retrieve all messages by account ID
     * GET /accounts/{account_id}/messages
     * always status 200
     */
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesFromUser(@PathVariable Integer account_id){
        List<Message> newList = messageService.getAllMessagesByAccountId(account_id);
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }
}
