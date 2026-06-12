
package br.org.edu.ifrn.LojaCarro.services;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    public CarroRepository carroRepository;

    // Métodos para teste:

    public Carro saveTest(Carro c) {

        if ("Honda".equalsIgnoreCase(c.getMarca())) {
            return carroRepository.save(c);
        }
        throw new RuntimeException("Falhou");
    }

    // Novo método para deletar por ID
    public void deleteByIdTest(Long id) {

      Optional<Carro> carro =  carroRepository.findById(id);

      if (carro.isPresent()){
           carroRepository.deleteById(id);
           return;
      }
        throw new RuntimeException("Falhou");
    }

    // Novo método para pesquisar por ID
    public Optional<Carro> findByIdTest(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        if (carro.isPresent()) {
            return carro;
        }
        throw new RuntimeException("Falhou");
    }

    // Novo método para listar todos os carros
    public List<Carro> findAllTest() {
        List<Carro> carros = carroRepository.findAll();

        if (carros.isEmpty()) {
            throw new RuntimeException(
                    "Nenhum carro encontrado."
            );
        }

        return carros;
    }
    // Método para atualizar (usa o save existente, mas pode ser renomeado se preferir)
    public Carro updateTest(Carro c) {

        if("Honda".equalsIgnoreCase(c.getMarca())){
            return carroRepository.save(c);
        }

        throw new RuntimeException("Falhou");
    }
}