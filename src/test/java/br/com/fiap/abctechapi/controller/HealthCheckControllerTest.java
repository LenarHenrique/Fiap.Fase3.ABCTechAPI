package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.component.VersionComponent;
import br.com.fiap.abctechapi.model.Version;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HealthCheckControllerTest {

    private HealthCheckController healthCheckController;
    private VersionComponent component;

    @Before
    public void before(){
        component = mock(VersionComponent.class);
        healthCheckController = new HealthCheckController(component);
    }

    @Test
    public void whenInvokeStatusMethosThenReturnResponseEntityOk(){
        assertEquals(ResponseEntity.ok().build(), healthCheckController.status());
    }

    @Test
    public void whenInvokeVersionMethodThenReturnResponseEntityOk(){
        Version expectedVersion = new Version("","1.0.0");

        when(component.getProjectVersio()).thenReturn(expectedVersion);

        assertEquals(ResponseEntity.ok(expectedVersion), healthCheckController.version());
    }
}