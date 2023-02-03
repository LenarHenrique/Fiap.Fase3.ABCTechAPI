package br.com.fiap.abctechapi.model;

import lombok.Data;

import javax.lang.model.element.Name;
import javax.persistence.*;

@Entity
@Data
@Table(name = "Assistances")
public class Assist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;
}
