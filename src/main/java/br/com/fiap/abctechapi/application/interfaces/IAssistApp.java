package br.com.fiap.abctechapi.application.interfaces;

import br.com.fiap.abctechapi.application.dto.AssistDTO;

import java.util.List;

public interface IAssistApp {
    List<AssistDTO> getAssists();
}
