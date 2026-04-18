package com.desafio.processoseletivo.model.dto;

import com.desafio.processoseletivo.model.Cidade;

public class CityDTO {
    private Long id;
    private String nome;

    
    public CityDTO() {}

   
    public CityDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
    }

   
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
