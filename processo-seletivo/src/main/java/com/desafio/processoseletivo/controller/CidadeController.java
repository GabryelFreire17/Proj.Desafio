package com.desafio.processoseletivo.controller;

import com.desafio.processoseletivo.model.dto.CityDTO;
import com.desafio.processoseletivo.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cidade")
@CrossOrigin("*")
public class CidadeController {

    @Autowired
    private CidadeService service;

   
    @GetMapping
    public List<CityDTO> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> buscarPorId(@PathVariable Long id) {
        CityDTO dto = service.buscarPorId(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CityDTO> salvar(@RequestBody CityDTO dto) {
        CityDTO salvo = service.salvar(dto);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
