package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinAssistsException;
import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

        if(!(arrayAssists.size() > order.getMIN_ASSISTS_GT())){
            throw new MinAssistsException("Invalid assists", "Adicione ao menos uma assistencia");
        }
        if(arrayAssists.size() > order.getMAX_ASSISTS()){
            throw new MaxAssistsException("Invalid assists", "Numero maximo de assistencias excedido!");
        }

        arrayAssists.forEach( i -> {
            Assist auxAssist = assistRepository.findById(i).orElseThrow();
            assistArrayList.add(auxAssist);
        });
        order.setServices(assistArrayList);

        if(!order.hasMinAssists()){
            throw new MinAssistsException("Invalid assists", "Adicione ao menos uma assistencia!");
        }
        if(order.exceedsMaxAssists()){
            throw new MaxAssistsException("Invalid assists", "Numero maximo de assistencias excedido!");
        }

        orderRepository.save(order);
    }
}
