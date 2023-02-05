package br.com.fiap.abctechapi.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@AllArgsConstructor
public class OrderLocationDTO {
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @PastOrPresent
    private Date dateTime;
}
