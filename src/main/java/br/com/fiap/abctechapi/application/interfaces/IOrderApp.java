package br.com.fiap.abctechapi.application.interfaces;

import br.com.fiap.abctechapi.application.dto.OrderDTO;

public interface IOrderApp {
    void createOrder(OrderDTO orderDTO);

}
