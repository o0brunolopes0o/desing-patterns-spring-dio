package dio.lab_padroes_projeto_spring.service;

import dio.lab_padroes_projeto_spring.model.Cliente;
import dio.lab_padroes_projeto_spring.model.Endereco;
import dio.lab_padroes_projeto_spring.repository.ClienteRepository;
import dio.lab_padroes_projeto_spring.repository.EnderecoRepository;

import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {
    private ClienteRepository clienteRepository;
    private EnderecoRepository enderecoRepository;
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public Cliente inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
        return cliente;
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(enderecoRepository.findById(cep).get());
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizar(Cliente cliente, Long id) {
        Optional<Cliente> clienteBusca = clienteRepository.findById(id);
        if (clienteBusca.isPresent()) {
            salvarClienteComCep(cliente);
        }

        return cliente;
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
