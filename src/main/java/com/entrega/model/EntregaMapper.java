package com.entrega.model;

import com.entrega.model.dto.ClienteDTO;
import com.entrega.model.dto.EnderecoDTO;
import com.entrega.model.dto.EntregaDTO;
import com.entrega.model.dto.PedidoProdutoDTO;
import com.entrega.model.entity.Cliente;
import com.entrega.model.entity.Endereco;
import com.entrega.model.entity.Entrega;
import com.entrega.model.entity.PedidoProduto;

import java.util.List;
import java.util.stream.Collectors;

public class EntregaMapper {

    public static Entrega mapToEntrega(EntregaDTO entregaDTO) {
        Entrega entrega = new Entrega();

        if (entregaDTO != null) {
            if (entregaDTO.getIdPedido() != null) {
                entrega.setIdPedido(entregaDTO.getIdPedido());
            }

            if (entregaDTO.getCliente() != null) {
                entrega.setCliente(transformaCliente(entregaDTO.getCliente()));
            }

            if (entregaDTO.getDataPedido() != null) {
                entrega.setDataPedido(entregaDTO.getDataPedido());
            }

            if (entregaDTO.getFormaPagamento() != null) {
                entrega.setFormaPagamento(entregaDTO.getFormaPagamento());
            }

            if (entregaDTO.getFormaPagamento() != null) {
                entrega.setFormaPagamento(entregaDTO.getFormaPagamento());
            }

            if (entregaDTO.getValidadeFormaPagamentoCartao() != null) {
                entrega.setValidadeFormaPagamentoCartao(entregaDTO.getValidadeFormaPagamentoCartao());
            }

            if (entregaDTO.getPedidoProdutos() != null) {
                entrega.setPedidoProdutos(transformaProdutos(entregaDTO.getPedidoProdutos()));
            }

            if (!Double.isNaN(entregaDTO.getTotalPedido())) {
                entrega.setTotalPedido(entregaDTO.getTotalPedido());
            }
        }

        return entrega;
    }

    private static Cliente transformaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(Long.valueOf(clienteDTO.getId())); // Supondo que o ID seja uma String
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(transformaEndereco(clienteDTO.getEndereco()));
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        cliente.setCpf(clienteDTO.getCpf());
        return cliente;
    }

    private static Endereco transformaEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setCep(enderecoDTO.getCep());
        return endereco;
    }
    private static List<PedidoProduto> transformaProdutos(List<PedidoProdutoDTO> produtosList) {
        return produtosList.stream().map(dto -> {
            PedidoProduto produto = new PedidoProduto();
            produto.setIdproduto(dto.getIdproduto());
            produto.setDescricao(dto.getDescricao());
            produto.setQuantidade(dto.getQuantidade());
            produto.setPreco(dto.getPreco());
            return produto;
        }).collect(Collectors.toList());
    }
}