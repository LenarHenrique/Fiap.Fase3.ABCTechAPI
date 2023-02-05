package br.com.fiap.abctechapi.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AssistDTO {

    private Long id;
    private String name;
    private String description;
}
