package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.dto.OrderDTO;
import br.com.fiap.abctechapi.application.interfaces.IOrderApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private IOrderApp orderApp;

    @Autowired
    public OrderController(IOrderApp orderApp){
        this.orderApp = orderApp;
    }

    @PostMapping
    public ResponseEntity saveOrder(@RequestBody OrderDTO request) throws Exception{
        orderApp.createOrder(request);
        return ResponseEntity.ok().build();
    }
}
