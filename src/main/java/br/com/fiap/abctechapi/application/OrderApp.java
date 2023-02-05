package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OrderDTO;
import br.com.fiap.abctechapi.application.interfaces.IOrderApp;
import org.springframework.stereotype.Component;

@Component
public class OrderApp implements IOrderApp {
    @Override
    public void createOrder(OrderDTO orderDTO) {

    }
}
