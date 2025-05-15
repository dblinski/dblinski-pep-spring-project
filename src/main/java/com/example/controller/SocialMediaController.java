package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
    /*
     * 1. new user registration
     * POST /register
     * if ok, status 200; if unsuccessful because duplicate username, status 409 (conflict); otherwise, status 400 (client error)
     * 
     * 2. user login
     * POST /login
     * if successful, status 200; otherwise, status 401 (unauthorized)
     * 
     * 3. create new message
     * POST /messages
     * if successful, status 200; otherwise, status 400 (client error)
     * 
     * 4. retrieve all messages
     * GET /messages
     * always status 200
     * 
     * 5. retrieve message by ID
     * GET /messages/{message_id}
     * always status 200
     * 
     * 6. delete message by message ID
     * DELETE /messages/{message_id}
     * always status 200
     * 
     * 7. update message by message ID
     * PATCH /messages/{message_id}
     * if successful, status 200; otherwise, status 400 (client error)
     * 
     * 8. retrieve all messages by account ID
     * GET /accounts/{account_id}/messages
     * always status 200
     */
}
