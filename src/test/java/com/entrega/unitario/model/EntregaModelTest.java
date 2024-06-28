package com.entrega.unitario.model;

import com.entrega.model.entity.Cliente;
import com.entrega.model.entity.Endereco;
import com.entrega.model.entity.PedidoProduto;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class EntregaModelTest {
    @Test
    void deveMapearClienteComSucesso() {
        // Criação de um objeto Endereco simulado
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setEstado("Estado Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setCep("00000-000");

        // Criação de um objeto Cliente simulado
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Nome Teste");
        cliente.setEmail("email@teste.com");
        cliente.setTelefone("123456789");
        cliente.setEndereco(endereco);
        cliente.setDataNascimento("01/01/2000");
        cliente.setCpf("000.000.000-00");

        // Validação dos getters
        assertEquals(Long.valueOf(1L), cliente.getId());
        assertEquals("Nome Teste", cliente.getNome());
        assertEquals("email@teste.com", cliente.getEmail());
        assertEquals("123456789", cliente.getTelefone());
        assertEquals(endereco, cliente.getEndereco());
        assertEquals("01/01/2000", cliente.getDataNascimento());
        assertEquals("000.000.000-00", cliente.getCpf());
    }
    @Test
    void deveMapearEnderecoComSucesso() {
        // Criação de um objeto Endereco simulado
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setEstado("Estado Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setCep("00000-000");

        // Validação dos getters
        assertEquals("Rua Teste", endereco.getLogradouro());
        assertEquals("Bairro Teste", endereco.getBairro());
        assertEquals("Estado Teste", endereco.getEstado());
        assertEquals("Cidade Teste", endereco.getCidade());
        assertEquals("00000-000", endereco.getCep());
    }
    @Test
    void deveMapearPedidoProdutoComSucesso() {
        // Criação de um objeto PedidoProduto simulado
        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setIdproduto(1L);
        pedidoProduto.setDescricao("Produto Teste");
        pedidoProduto.setQuantidade(2);
        pedidoProduto.setPreco(100.0);

        // Validação dos getters
        assertEquals(Long.valueOf(1L), pedidoProduto.getIdproduto());
        assertEquals("Produto Teste", pedidoProduto.getDescricao());
        assertEquals(2, pedidoProduto.getQuantidade());
        assertEquals(100.0, pedidoProduto.getPreco(), 0.01);
    }
}
