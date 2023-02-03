package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.component.VersionComponent;
import br.com.fiap.abctechapi.model.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheckController {
    private VersionComponent component;
    public HealthCheckController(@Autowired VersionComponent component){
        this.component = component;

    }

    @GetMapping
    public ResponseEntity<Object> status(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("version")
    public ResponseEntity<Version> version()  {
        return ResponseEntity.ok(this.component.getProjectVersion());
    }
}
