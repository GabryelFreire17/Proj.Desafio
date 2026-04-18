package com.desafio.processoseletivo;

import com.desafio.processoseletivo.model.Cidade;
import com.desafio.processoseletivo.repository.CidadeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProcessoSeletivoApplication {

    public static void main(String[] args) {
       
        SpringApplication.run(ProcessoSeletivoApplication.class, args);
    }

    @Bean
CommandLineRunner init(CidadeRepository repository) {
    return args -> {
        repository.deleteAll(); 
        Cidade c = new Cidade();
        c.setNome("Cidade Sede");
        
        repository.save(c); 
        System.out.println(">>> Backend pronto: Cidade 1 criada.");
    };
}

}
