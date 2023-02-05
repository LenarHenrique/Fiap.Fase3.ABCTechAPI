package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OrderDTO;
import br.com.fiap.abctechapi.application.dto.OrderLocationDTO;
import br.com.fiap.abctechapi.application.interfaces.IOrderApp;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import br.com.fiap.abctechapi.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderApp implements IOrderApp {

    private final OrderService orderService;
    @Autowired
    public OrderApp(OrderService orderService){
        this.orderService = orderService;
    }
    @Override
    public void createOrder(OrderDTO orderDTO) throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(orderDTO.getOperatorId());
        if(orderDTO.getStart() != null){
            newOrder.setStartOrderLocation(getOrderLocationFromDTO(orderDTO.getStart()));
        }
        if(orderDTO.getEnd() != null){
            newOrder.setEndOrderLocation(getOrderLocationFromDTO(orderDTO.getEnd()));
        }

        this.orderService.saveOrder(newOrder, orderDTO.getAssists());

    }

    private OrderLocation getOrderLocationFromDTO(OrderLocationDTO orderLocationDTO){
        OrderLocation location = new OrderLocation();
        location.setLatitude(orderLocationDTO.getLatitude());
        location.setLongitude(orderLocationDTO.getLongitude());
        location.setDate(orderLocationDTO.getDateTime());
        return location;
    }
}
