package br.org.edu.ifrn.LojaCarro.services;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.repository.CarroRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class CarroControllerTest {

    @Autowired
    private CarroService carroService;

    @Autowired
    private CarroRepository carroRepository;

    @BeforeEach
    void setup() {
        carroRepository.deleteAll();
    }

    @Test
    void salvarCarroSucesso() {
        Carro carro = new Carro();
        carro.setModelo("Civic");
        carro.setMarca("Honda");
        carro.setAno(2022);

        Carro resultado = carroService.saveTest(carro);

        assertEquals("Civic", resultado.getModelo());
        assertEquals("Honda", resultado.getMarca());
        assertEquals(2022, resultado.getAno());

        System.out.println("HTTP 200 OK");
    }

    @Test
    void salvarCarroFalha() {
        Carro carro = new Carro();
        carro.setModelo("SW4");
        carro.setMarca("Toyota");
        carro.setAno(2022);

        assertThrows(RuntimeException.class, () -> {
            carroService.saveTest(carro);
        });
        System.out.println("HTTP 500 OK");
    }

    @Test
    void buscarCarroPorIdSucesso() {
        // Salva primeiro carro (recebe ID = 1)
        Carro c1 = new Carro();
        c1.setMarca("Honda");
        c1.setModelo("Civic");
        c1.setAno(2022);
        carroRepository.save(c1);

        Carro c2 = new Carro();
        c2.setMarca("Honda");
        c2.setModelo("City");
        c2.setAno(2022);
        Carro salvo2 = carroRepository.save(c2);

        Optional<Carro> resultado = carroService.findByIdTest(salvo2.getId());

        assertEquals(salvo2.getId(), resultado.get().getId());
        assertEquals("City", resultado.get().getModelo());
        assertEquals("Honda", resultado.get().getMarca());
        System.out.println("HTTP 200 OK");
    }
    @Test
    void buscarCarroPorIdFalha() {
        assertThrows(RuntimeException.class, () -> {
            carroService.findByIdTest(999L);
        });
        System.out.println("HTTP 500 OK");
    }

    @Test
    void listarCarrosSucesso() {
        Carro carro1 = new Carro();
        carro1.setModelo("Civic");
        carro1.setMarca("Honda");
        carro1.setAno(2022);

        Carro carro2 = new Carro();
        carro2.setModelo("City");
        carro2.setMarca("Honda");
        carro2.setAno(2022);

        carroService.saveTest(carro1);
        carroService.saveTest(carro2);

        List<Carro> lista = carroService.findAllTest();
        assertEquals(2, lista.size());
        System.out.println("HTTP 200 OK");
    }

    @Test
    void listarCarrosFalha() {
        assertThrows(RuntimeException.class, () -> {
            carroService.findAllTest(); // Método de teste que lança exceção se lista vazia
        });
        System.out.println("HTTP 500 OK");
    }

    @Test
    void atualizarCarroSucesso() {
        Carro carro = new Carro();
        carro.setModelo("City");
        carro.setMarca("Honda");
        carro.setAno(2022);

        Carro salvo = carroService.saveTest(carro);

        salvo.setModelo("Civic");
        salvo.setAno(2025);

        Carro atualizado = carroService.updateTest(salvo);

        assertEquals("Civic", atualizado.getModelo());
        assertEquals(2025, atualizado.getAno());
        assertEquals("Honda", atualizado.getMarca());
        System.out.println("HTTP 200 OK");
    }

    @Test
    void atualizarCarroFalha() {
        Carro carro = new Carro();
        carro.setModelo("Uno");
        carro.setMarca("Fiat");

        assertThrows(RuntimeException.class, () -> {
            carroService.updateTest(carro);
        });
        System.out.println("HTTP 500 OK");
    }

    @Test
    void apagarCarroSucesso() {
        Carro carro1 = new Carro();
        carro1.setModelo("Civic");
        carro1.setMarca("Honda");
        carro1.setAno(2022);

        Carro carro2 = new Carro();
        carro2.setModelo("City");
        carro2.setMarca("Honda");
        carro2.setAno(2022);

        Carro salvo1 = carroService.saveTest(carro1);
        Carro salvo2 = carroService.saveTest(carro2);

        carroService.deleteByIdTest(salvo2.getId());

        assertEquals(1, carroService.findAllTest().size());
        System.out.println("HTTP 200 OK");
    }

    @Test
    void apagarCarroFalha() {
        assertThrows(RuntimeException.class, () -> {
            carroService.deleteByIdTest(999L);
        });
        System.out.println("HTTP 500 OK");
    }
}