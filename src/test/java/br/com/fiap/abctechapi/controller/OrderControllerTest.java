package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.OrderApp;
import br.com.fiap.abctechapi.application.dto.OrderDTO;
import br.com.fiap.abctechapi.application.interfaces.IOrderApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;

public class OrderControllerTest {

    private OrderController orderController;

    private IOrderApp orderApp;

    @Before
    public void setUp() {
        orderApp = mock(OrderApp.class);
        orderController = new OrderController(orderApp);
    }

    @Test
    public void whenSaveOrderControllerInvokedThenReturnCorrectResponseEntity() throws Exception {
       ResponseEntity<?> responseEntity = orderController.saveOrder(new OrderDTO());

        Assert.assertEquals(ResponseEntity.ok().build(), responseEntity);
    }
}