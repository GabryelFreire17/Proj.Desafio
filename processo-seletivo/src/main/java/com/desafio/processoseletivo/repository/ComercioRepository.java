package com.desafio.processoseletivo.repository;

import com.desafio.processoseletivo.model.Comercio;
import com.desafio.processoseletivo.model.TipoComercio; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {

    // 1. Trava por nome
    boolean existsByNomeComercioAndCidadeId(String nomeComercio, Long cidadeId);

    // 2. Trava por tipo (FARMACIA, PADARIA, etc)
    boolean existsByTipoAndCidadeId(TipoComercio tipo, Long cidadeId);
}
