package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.AssistDTO;
import br.com.fiap.abctechapi.application.interfaces.IAssistApp;
import br.com.fiap.abctechapi.service.AssistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssistApp implements IAssistApp {
    private final AssistService assistService;

    public AssistApp(@Autowired AssistService assistService){
        this.assistService = assistService;
    }
    @Override
    public List<AssistDTO> getAssists() {
        return assistService.getServiceList().stream()
                .map(ass -> new AssistDTO(
                        ass.getId(),
                        ass.getName(),
                        ass.getDescription())
                )
                .collect(Collectors.toList());
    }
}
