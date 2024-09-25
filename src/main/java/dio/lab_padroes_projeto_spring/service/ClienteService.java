package dio.lab_padroes_projeto_spring.service;

import dio.lab_padroes_projeto_spring.model.Cliente;
import java.util.Optional;

public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Optional<Cliente> buscarPorId(Long id);

    Cliente inserir(Cliente cliente);
    Cliente atualizar(Cliente cliente, Long id);
    void deletar(Long id);
}
