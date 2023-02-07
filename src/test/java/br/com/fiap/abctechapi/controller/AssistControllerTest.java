package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.AssistApp;
import br.com.fiap.abctechapi.application.dto.AssistDTO;
import br.com.fiap.abctechapi.application.interfaces.IAssistApp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AssistControllerTest {

    private AssistController assistController;

    private IAssistApp iAssistApp;

    @Before
    public void setUp() {
        iAssistApp = mock(AssistApp.class);
        assistController = new AssistController(iAssistApp);
    }

    @Test
    public void whenGetAssistsControllerInvokedThenReturnCorrectResponseEntity() {
        AssistDTO assistDTO = new AssistDTO(1L, "controller", "Test Controller");
        List<AssistDTO> expectedList = Collections.singletonList(assistDTO);

        when(iAssistApp.getAssists()).thenReturn(expectedList);

        assertEquals(ResponseEntity.ok(expectedList), assistController.getAssists());
    }
}