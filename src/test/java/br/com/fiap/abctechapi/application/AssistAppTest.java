package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.service.AssistService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AssistAppTest {

    private AssistApp assistApp;

    private AssistService assistService;

    @Before
    public void setUp() {
        assistService = mock(AssistService.class);
        assistApp = new AssistApp(assistService);
    }

    @Test
    public void whenGetServiceListMethodIsInvokedThenAssetDtoShouldMappedCorrectly(){
        Assist assist1 = new Assist(1L, "Assit One", "The first assist");
        Assist assist2 = new Assist(2L, "Assit Two", "The second assist");

        List<Assist> assistList = Arrays.asList(assist1, assist2);
        when(assistService.getServiceList()).thenReturn(assistList);

        assertEquals(2, assistApp.getAssists().size());
        assertEquals(assist1.getId(), assistApp.getAssists().get(0).getId());
        assertEquals(assist1.getName(), assistApp.getAssists().get(0).getName());
        assertEquals(assist1.getDescription(), assistApp.getAssists().get(0).getDescription());
        assertEquals(assist2.getId(), assistApp.getAssists().get(1).getId());
        assertEquals(assist2.getName(), assistApp.getAssists().get(1).getName());
        assertEquals(assist2.getDescription(), assistApp.getAssists().get(1).getDescription());
    }
}