package com.desafio.processoseletivo.service;

import com.desafio.processoseletivo.model.Cidade;
import com.desafio.processoseletivo.model.dto.CityDTO;
import com.desafio.processoseletivo.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    
    public List<CityDTO> listarTodas() {
        return repository.findAll().stream()
                .map(cidade -> new CityDTO(cidade))
                .collect(Collectors.toList());
    }

    
    public CityDTO salvar(CityDTO dto) {
        Cidade cidade = new Cidade();
        cidade.setId(dto.getId()); 
        cidade.setNome(dto.getNome());
        
        cidade = repository.save(cidade);
        return new CityDTO(cidade);
    }

   
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public CityDTO buscarPorId(Long id) {
        Cidade cidade = repository.findById(id).orElse(null);
        return (cidade != null) ? new CityDTO(cidade) : null;
    }
}
