package dio.lab_padroes_projeto_spring.model;

import jakarta.persistence.*;

@Table(name = "tab_clientes") // Alterei para refletir que esta tabela armazena clientes
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToOne(cascade = CascadeType.ALL) // Adicionado o cascade para refletir alterações em Endereco
    @JoinColumn(name = "endereco_id") // Especifica a chave estrangeira
    private Endereco endereco;

    // Construtor vazio necessário para o JPA
    public Cliente() {
    }

    // Construtor para facilitar a criação de instâncias
    public Cliente(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
