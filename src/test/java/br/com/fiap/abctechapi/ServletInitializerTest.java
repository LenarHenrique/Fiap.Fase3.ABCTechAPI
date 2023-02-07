package br.com.fiap.abctechapi;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.junit.Assert.assertEquals;

public class ServletInitializerTest {

    private ServletInitializer servletInitializer;


    @Before
    public void setUp(){
        servletInitializer = new ServletInitializer();
    }

    @Test
    public void whenConfigureMethodInvokedThenReturnOneSource() {
        SpringApplicationBuilder springApplicationBuilder = servletInitializer.configure(new SpringApplicationBuilder());

        assertEquals(1,  springApplicationBuilder.build().getAllSources().size());
    }
}