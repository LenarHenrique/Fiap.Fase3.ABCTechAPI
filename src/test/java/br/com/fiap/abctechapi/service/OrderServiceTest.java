package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinAssistsException;
import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.impl.OrderService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static  org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AssistRepository assistRepository;
    private OrderService orderService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.orderService = new OrderService(orderRepository, assistRepository);
        when(assistRepository.findById(any()))
                .thenReturn(Optional.of(new Assist(1L, "TESTE", "Desc")));
    }

    @Test
    public void order_service_not_null(){
        Assertions.assertNotNull(orderService);
    }

    @Test
    public  void create_order_success() throws Exception{
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        orderService.saveOrder(newOrder, List.of(1L));

        verify(orderRepository, times(1)).save(newOrder);
    }

    @Test
    public void create_order_erro_minimum()throws Exception{
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        Assertions.assertThrows(MinAssistsException.class, () -> orderService.saveOrder(newOrder, List.of()));

        verify(orderRepository, times(0)).save(newOrder);
    }
    @Test
    public void create_order_erro_maximum()throws Exception{
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        Assertions.assertThrows(MaxAssistsException.class, () -> orderService.saveOrder(newOrder, List.of(1L,2L,3L,4L,5L,6L,1L,2L,3L,4L,5L,6L,1L,2L,3L,4L)));

        verify(orderRepository, times(0)).save(newOrder);
    }

}
