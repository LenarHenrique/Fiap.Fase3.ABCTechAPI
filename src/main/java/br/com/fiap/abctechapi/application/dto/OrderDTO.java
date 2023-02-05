package br.com.fiap.abctechapi.application.dto;

import br.com.fiap.abctechapi.model.OrderLocation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long operatorId;
    private List<Long> assists;
    private OrderLocationDTO start;
    private OrderLocationDTO end;
}
