package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinAssistsException;
import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.impl.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AssistRepository assistRepository;

    private IOrderService orderService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository, assistRepository);
        when(assistRepository.findById(any())).
                thenReturn(Optional.of(new Assist(1L, "Teste", "Description Test")));
    }

    @Test
    public void order_service_not_null(){
        Assertions.assertNotNull(orderService);
    }

    @Test
    public void create_order_success() throws Exception{
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        orderService.saveOrder(newOrder, List.of(1L));

        verify(orderRepository, times(1)).save(newOrder);
    }

    @Test
    public void create_order_error_minimum() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        assertThrows(MinAssistsException.class, () -> orderService.saveOrder(newOrder, List.of()));
        verify(orderRepository, times(0)).save(newOrder);

    }

    @Test
    public void create_order_error_maximum() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        assertThrows(MaxAssistsException.class, () -> orderService.saveOrder(newOrder, List.of(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L,11L,12L,13L,14L,15L,16L)));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    public void whenOrderHasMinAssistsThenThrowErrorOfMinAssistsException() throws Exception{
        Order newOrder = Mockito.spy(Order.class);
        newOrder.setOperatorId(1234L);
        newOrder.setServices(Collections.emptyList());

        when(assistRepository.findById(any())).
                thenReturn(Optional.of(new Assist(1L, "Teste", "Description Test")));

        when(newOrder.hasMinAssists()).thenReturn(false);

        MinAssistsException exception = assertThrows(MinAssistsException.class, () -> {
            orderService.saveOrder(newOrder, List.of(1L));
        });

        verify(orderRepository, times(0)).save(newOrder);
        assertEquals("Invalid assists", exception.getMessage());
        assertEquals("Adicione ao menos uma assistencia!", exception.getDescription());
    }

    @Test
    public void whenOrderExceedsMaxAssistsThenThrowErrorOfMaxAssistsException(){
        Order newOrder = Mockito.spy(Order.class);
        newOrder.setOperatorId(1234L);
        newOrder.setServices(Collections.emptyList());

        when(assistRepository.findById(any())).
                thenReturn(Optional.of(new Assist(1L, "Teste", "Description Test")));

        when(newOrder.hasMinAssists()).thenReturn(true);
        when(newOrder.exceedsMaxAssists()).thenReturn(true);

        MaxAssistsException exception = assertThrows(MaxAssistsException.class, () -> {
            orderService.saveOrder(newOrder, List.of(1L));
        });

        verify(orderRepository, times(0)).save(newOrder);
        assertEquals("Invalid assists", exception.getMessage());
        assertEquals("Numero maximo de assistencias excedido!", exception.getDescription());
    }
}