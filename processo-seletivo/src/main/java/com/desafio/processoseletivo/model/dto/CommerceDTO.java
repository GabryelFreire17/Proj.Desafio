package com.desafio.processoseletivo.model.dto;

import com.desafio.processoseletivo.model.Comercio;

public class CommerceDTO {
    private Long id;
    private String nome;
    private String nomeResponsavel; 
    private String tipoComercio;
    private Long cidadeId;

    public CommerceDTO() {}

    public CommerceDTO(Comercio comercio) {
        this.id = comercio.getId();
        this.nome = comercio.getNomeComercio();
        this.nomeResponsavel = comercio.getNomeResponsavel(); 
        this.tipoComercio = (comercio.getTipo() != null) ? comercio.getTipo().name() : null;
        if (comercio.getCidade() != null) {
            this.cidadeId = comercio.getCidade().getId();
        }
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }

    public String getTipoComercio() { return tipoComercio; }
    public void setTipoComercio(String tipoComercio) { this.tipoComercio = tipoComercio; }

    public Long getCidadeId() { return cidadeId; }
    public void setCidadeId(Long cidadeId) { this.cidadeId = cidadeId; }
}
