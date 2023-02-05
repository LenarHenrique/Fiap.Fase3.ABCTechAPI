package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderService implements IOrderService {

    private OrderRepository orderRepository;
    private AssistRepository assistRepository;

    @Autowired
    public OrderService (
            OrderRepository orderRepository,
            AssistRepository assistRepository
    ){
        this.orderRepository = orderRepository;
        this.assistRepository = assistRepository;
    }

    @Override
    public void saveOrder(Order order, List<Long> arrayAssists) throws Exception {
        ArrayList<Assist> assistArrayList = new ArrayList<>();

        arrayAssists.forEach( i -> {
            Assist auxAssist = assistRepository.findById(i).orElseThrow();
            assistArrayList.add(auxAssist);
        });
        order.setServices(assistArrayList);

        if(!order.hasMinAssists()){
            throw new Exception();
        }
        if(order.exceedsMaxAssists()){
            throw new Exception();
        }

        orderRepository.save(order);
    }
}
