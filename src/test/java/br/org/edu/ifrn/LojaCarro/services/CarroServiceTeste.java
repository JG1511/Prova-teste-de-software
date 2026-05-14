package br.org.edu.ifrn.LojaCarro.services;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CarroServiceTeste {

    @Autowired
    private CarroService carroService;

    @Test
    void devSalvarCarro() {
        Carro carro = new Carro();
        carro.setModelo("Corola");
        carro.setMarca("Toyota");
        carro.setAno(2022);

        Carro resultado = carroService.save(carro);

        assertEquals("Corola", resultado.getModelo());
        assertEquals("Toyota", resultado.getMarca());
        assertEquals(2022, resultado.getAno());
    }

    @Test
    void devBuscarCarroPorId() {
        Carro carro = new Carro();
        carro.setModelo("Civic");
        carro.setMarca("Honda");
        carro.setAno(2022);

        Carro carroSalvo = carroService.save(carro);
        Carro resultado = carroService.findById(carroSalvo.getId()).get();

        assertEquals(carroSalvo.getId(), resultado.getId());
        assertEquals("Civic", carroSalvo.getModelo());
        assertEquals("Honda", carroSalvo.getMarca());
        assertEquals(2022, carroSalvo.getAno());
    }
}