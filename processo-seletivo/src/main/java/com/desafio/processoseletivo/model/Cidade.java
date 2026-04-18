package com.desafio.processoseletivo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "cidade")
public class Cidade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome da cidade é obrigatório")
    private String nome;

     @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Comercio> comercios;

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
