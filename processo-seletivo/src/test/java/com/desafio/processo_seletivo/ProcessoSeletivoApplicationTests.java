package com.desafio.processo_seletivo;

import com.desafio.processoseletivo.repository.ComercioRepository;
import com.desafio.processoseletivo.service.ComercioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ComercioServiceTest {

    @Mock
    private ComercioRepository repository;

    @InjectMocks
    private ComercioService service;

    @Test
    void contextLoads() {
        assertNotNull(service);
    }
}
