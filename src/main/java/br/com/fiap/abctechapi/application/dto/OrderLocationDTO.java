package br.com.fiap.abctechapi.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLocationDTO {
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @PastOrPresent
    private Date dateTime;
}
