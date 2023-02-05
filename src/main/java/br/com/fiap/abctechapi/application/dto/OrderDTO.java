package br.com.fiap.abctechapi.application.dto;

import br.com.fiap.abctechapi.model.OrderLocation;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    @NotNull
    @Positive
    private Long operatorId;
    @NotEmpty
    private List<Long> assists;
    @Valid
    private OrderLocationDTO start;
    @Valid
    private OrderLocationDTO end;
}
