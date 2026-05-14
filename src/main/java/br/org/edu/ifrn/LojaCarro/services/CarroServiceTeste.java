package br.org.edu.ifrn.LojaCarro.services;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class CarroServiceTeste {


    // Injeção de dependencia?
    @Autowired
    private  CarroService carroService;

    @Test
    void devSalvarCarro(){
        Carro carro = new Carro();
        carro.setModelo("Corola");
        carro.setAno(2022);


        Carro resultado = carroService.save(carro);

        assertEquals("Corola", resultado.getModelo());
        assertEquals(2022, resultado.getAno());
    }

    @Test
    void devBuscarCarroPorId(){
        Carro carro = new Carro();
        carro.setModelo("Civic");
        carro.setAno(2022);


        Carro carroSalvo = carroService.save(carro);
        Carro resultado = carroService.findById(carroSalvo.getId()).get();

        assertEquals(carroSalvo.getId(), resultado.getId());
    }
}
