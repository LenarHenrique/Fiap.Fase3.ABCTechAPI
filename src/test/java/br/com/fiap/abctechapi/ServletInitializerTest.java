package br.com.fiap.abctechapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServletInitializerTest {

    private ServletInitializer servletInitializer;


    @BeforeEach
    public void setUp(){
        servletInitializer = new ServletInitializer();
    }

    @Test
    public void whenConfigureMethodInvokedThenReturnOneSource() {
        SpringApplicationBuilder springApplicationBuilder = servletInitializer.configure(new SpringApplicationBuilder());

        assertEquals(1, springApplicationBuilder.build().getAllSources().size());
    }
}