package com.desafio.processoseletivo.service;

import com.desafio.processoseletivo.model.Comercio;
import com.desafio.processoseletivo.model.TipoComercio;
import com.desafio.processoseletivo.model.Cidade;
import com.desafio.processoseletivo.model.dto.CommerceDTO;
import com.desafio.processoseletivo.repository.ComercioRepository;
import com.desafio.processoseletivo.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComercioService {

    @Autowired
    private ComercioRepository repository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<CommerceDTO> listarTodos() {
        return repository.findAll().stream()
                .map(comercio -> new CommerceDTO(comercio))
                .collect(Collectors.toList());
    }

    public CommerceDTO salvar(CommerceDTO dto) {
        // 1. Converte a String do DTO para o Enum para podermos validar
        TipoComercio tipoEnum = (dto.getTipoComercio() != null) ? TipoComercio.valueOf(dto.getTipoComercio()) : null;

        // 2. Trava de Nome: Verifica se já existe esse NOME na mesma cidade
        boolean nomeExiste = repository.existsByNomeComercioAndCidadeId(dto.getNome(), dto.getCidadeId());
        
        // 3. Trava de Tipo: Verifica se já existe esse TIPO na mesma cidade
        boolean tipoExiste = (tipoEnum != null) && repository.existsByTipoAndCidadeId(tipoEnum, dto.getCidadeId());

        // Só barramos se for um NOVO cadastro (id null)
        if (dto.getId() == null) {
            if (nomeExiste) {
                throw new RuntimeException("Este nome de comércio já está cadastrado nesta cidade!");
            }
            if (tipoExiste) {
                throw new RuntimeException("Já existe um comércio do tipo " + dto.getTipoComercio() + " nesta cidade!");
            }
        }

        Comercio comercio = new Comercio();
        comercio.setId(dto.getId());
        comercio.setNomeComercio(dto.getNome());
        comercio.setNomeResponsavel(dto.getNomeResponsavel());
        comercio.setTipo(tipoEnum);

        if (dto.getCidadeId() != null) {
            Cidade cidade = cidadeRepository.findById(dto.getCidadeId()).orElse(null);
            comercio.setCidade(cidade);
        }

        return new CommerceDTO(repository.save(comercio));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
