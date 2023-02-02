package br.com.fiap.abctechapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RestController
@RequestMapping("/")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<Object> status(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("version")
    public ResponseEntity<String> version() throws IOException {
        Properties prop = new Properties();
        InputStream imput = getClass().getClassLoader().getResourceAsStream("application.yml");
        prop.load(imput);
        return ResponseEntity.ok(prop.getProperty("build.name") + ": " + prop.getProperty("build.version"));
    }
}
