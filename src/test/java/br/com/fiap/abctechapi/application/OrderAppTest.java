package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OrderDTO;
import br.com.fiap.abctechapi.application.dto.OrderLocationDTO;
import br.com.fiap.abctechapi.service.impl.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

public class OrderAppTest {

    private OrderApp orderApp;
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = mock(OrderService.class);
        orderApp = new OrderApp(orderService);
    }

    @Test
    public void whenCreateOrderMethodInvokedThenShouldCallMethodSaveOrder() throws Exception {

        OrderLocationDTO start = new OrderLocationDTO();
        OrderLocationDTO end = new OrderLocationDTO(Double.valueOf("0"), Double.valueOf("0"), new Date());

        OrderDTO orderDTO = new OrderDTO(1L, singletonList(1L),start, end );

        orderApp.createOrder(orderDTO);

        verify(orderService).saveOrder(any(), any());
    }

    @Test
    public void whenCreateOrderMethodInvokedAndStartNullThenShouldCallMethodSaveOrder() throws Exception {

        OrderLocationDTO end = new OrderLocationDTO();

        OrderDTO orderDTO = new OrderDTO(1L, singletonList(1L), null, end);

        orderApp.createOrder(orderDTO);

        verify(orderService).saveOrder(any(), any());
    }

    @Test
    public void whenCreateOrderMethodInvokedAndEndNullThenShouldCallMethodSaveOrder() throws Exception {

        OrderLocationDTO start = new OrderLocationDTO();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAssists(singletonList(1L));
        orderDTO.setOperatorId(1L);
        orderDTO.setStart(start);
        orderDTO.setEnd(null);

        orderApp.createOrder(orderDTO);

        verify(orderService).saveOrder(any(), any());
    }

    @Test
    public void whenCreateOrderMethodInvokedAndStartAndEndNullThenShouldCallMethodSaveOrder() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAssists(singletonList(1L));
        orderDTO.setOperatorId(1L);
        orderDTO.setStart(null);
        orderDTO.setEnd(null);

        orderApp.createOrder(orderDTO);

        verify(orderService).saveOrder(any(), any());
    }
}