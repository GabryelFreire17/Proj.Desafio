package com.desafio.processoseletivo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "comercio", uniqueConstraints = {
    // Bloqueia duplicidade de NOME por cidade
    @UniqueConstraint(columnNames = {"nome_comercio", "cidade_id"}),
    // Bloqueia duplicidade de TIPO por cidade
    @UniqueConstraint(columnNames = {"tipo", "cidade_id"})
})
public class Comercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome_comercio") 
    private String nomeComercio;

    @NotBlank
    @Column(name = "nome_responsavel") 
    private String nomeResponsavel;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoComercio tipo;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    
    public Comercio() {}

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeComercio() { return nomeComercio; }
    public void setNomeComercio(String nomeComercio) { this.nomeComercio = nomeComercio; }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }

    public TipoComercio getTipo() { return tipo; }
    public void setTipo(TipoComercio tipo) { this.tipo = tipo; }

    public Cidade getCidade() { return cidade; }
    public void setCidade(Cidade cidade) { this.cidade = cidade; }
}
