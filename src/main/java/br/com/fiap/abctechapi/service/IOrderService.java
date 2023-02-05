package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Order;

import java.util.List;

public interface IOrderService {
    void saveOrder(Order order, List<Long> arrayAssists) throws Exception;
}
