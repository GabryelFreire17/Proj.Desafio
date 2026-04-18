package com.desafio.processoseletivo.controller;

import com.desafio.processoseletivo.model.dto.CommerceDTO;
import com.desafio.processoseletivo.service.ComercioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comercio")
@CrossOrigin("*")
public class ComercioController {

    @Autowired
    private ComercioService service;

   
    @GetMapping
    public List<CommerceDTO> listarTodos() {
        return service.listarTodos();
    }

    
    @PostMapping
    public ResponseEntity<CommerceDTO> salvar(@RequestBody CommerceDTO dto) {
        CommerceDTO salvo = service.salvar(dto);
        return ResponseEntity.ok(salvo);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
