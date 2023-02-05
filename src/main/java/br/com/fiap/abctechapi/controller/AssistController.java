package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.dto.AssistDTO;
import br.com.fiap.abctechapi.application.interfaces.IAssistApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assists")
public class AssistController {
    private final IAssistApp app;

    @Autowired
    public AssistController( IAssistApp app){
        this.app = app;
    }

    @GetMapping
    public ResponseEntity<List<AssistDTO>> getAssists(){
        List<AssistDTO> assistList = app.getAssists();
        return ResponseEntity.ok(assistList);
    }


}
