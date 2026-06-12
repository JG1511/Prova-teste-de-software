# Projeto LojaCarro

## Descrição
API para gerenciamento de carros usando Spring Boot.

## Endpoints
- `POST /carro/salvar` — Salva um carro
- `GET /carro/{id}` — Busca carro por ID
- `GET /carro` — Lista todos os carros
- `PUT /carro/{id}` — Atualiza um carro
- `DELETE /carro/{id}` — Deleta um carro

Obs: Na pratica, foram criados "endpoints ficticios" para realização dos testes

Os testes estão em `src/test/java/br/org/edu/ifrn/LojaCarro/services/CarroControllerTest.java`.

## O que é preciso para rodar o teste
Na prática o teste ja populariza o bd, com o detalhe que, depois de cada teste o BD e limpo e volta para o estagio inical.
Isso acontece porque cada teste foi pensado para ser um caso unico.

### Métodos de teste

| Método | Descrição | Condições de sucesso | Condições de falha |
|--------|-----------|--------------------|------------------|
| `saveTest(Carro c)` | Salva um carro | Marca do carro = "Honda" | Qualquer outra marca lança `RuntimeException("Falhou")` |
| `deleteByIdTest(Long id)` | Deleta um carro por ID | O carro existe no banco | Se não existe, lança `RuntimeException("Falhou")` |
| `findByIdTest(Long id)` | Busca um carro por ID | ID existe no banco | Se não existe, lança `RuntimeException("Falhou")` |
| `findAllTest()` | Lista todos os carros | Existem carros no banco | Se não houver nenhum carro, lança `RuntimeException("Nenhum carro encontrado.")` |
| `updateTest(Carro c)` | Atualiza um carro existente | Marca do carro = "Honda" | Qualquer outra marca lança `RuntimeException("Falhou")` |

---

## Como utilizar os testes

1. O projeto deve estar configurado com **Spring Boot** e **Spring Data JPA**.
2. Os métodos de teste usam o `CarroRepository` para interagir com o banco.
3. Para testar falhas, basta passar dados que não atendem às condições de sucesso (ex.: marca diferente de "Honda" ou ID inexistente).

### Exemplos de uso

```java
Carro carro = new Carro();
carro.setMarca("Honda");
carro.setModelo("Civic");
carro.setAno(2022);

// Salvar carro - sucesso
Carro salvo = carroService.saveTest(carro);

// Buscar carro por ID - sucesso
Optional<Carro> resultado = carroService.findByIdTest(salvo.getId());

// Atualizar carro - falha
Carro carroErrado = new Carro();
carroErrado.setMarca("Toyota");
carroService.updateTest(carroErrado); // Lança RuntimeException
```
### Caso queira popularizar o BD (Lembrando que cada teste limpa o BD)
```mysql
-- Criação da tabela Carro
CREATE TABLE IF NOT EXISTS carro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(255) NOT NULL,
    marca VARCHAR(255) NOT NULL,
    ano INT NOT NULL
);

-- Inserindo carros válidos (passam no saveTest)
INSERT INTO carro (modelo, marca, ano) VALUES
('Civic', 'Honda', 2022),
('City', 'Honda', 2022),
('Fit', 'Honda', 2021);

-- Inserindo carros inválidos (não passam no saveTest)
INSERT INTO carro (modelo, marca, ano) VALUES
('Corola', 'Toyota', 2022),
('SW4', 'Toyota', 2023),
('Uno', 'Fiat', 2020);

-- Opcional: verificar o conteúdo
SELECT * FROM carro;

