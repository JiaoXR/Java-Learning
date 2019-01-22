package com.jaxer.example.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jaxer.example.ticket.service.TicketService;
import org.springframework.stereotype.Service;

/**
 * Created by jaxer on 2018/11/19
 */
@Service
public class UserService {
    @Reference
    private TicketService ticketService;

    public void hello() {
        String ticket = ticketService.getTicket();
        System.out.println("hello-->" + ticket);
    }
}
