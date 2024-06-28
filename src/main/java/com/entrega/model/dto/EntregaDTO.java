package com.entrega.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class EntregaDTO {
    @JsonProperty("idPedido")
    @NotBlank(message = "idPedido precisa ser enviado")
    private String idPedido;
    @NotNull(message = "O cliente não pode ser nulo.")
    @Valid
    private ClienteDTO cliente;
    @JsonProperty("dataPedido")
    @NotBlank(message = "dataPedido precisa ser enviado")
    private String dataPedido;
    @JsonProperty("totalPedido")
    @NotNull(message = "O total do pedido não pode ser nulo.")
    @Min(value = 0, message = "O total do pedido deve ser maior ou igual a zero.")
    private double totalPedido;
    @JsonProperty("formaPagamento")
    @NotBlank(message = "formaPagamento precisa ser enviado")
    private String formaPagamento;
    @JsonProperty("validadeFormaPagamentoCartao")
    @NotBlank(message = "validadeFormaPagamentoCartao precisa ser enviado")
    private String validadeFormaPagamentoCartao;
    @JsonProperty("pedidoProdutos")
    @NotEmpty(message = "A lista de pedidoProdutos não pode estar vazia.")
    @Valid
    private List<@NotNull PedidoProdutoDTO> pedidoProdutos;

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public String getValidadeFormaPagamentoCartao() {
        return validadeFormaPagamentoCartao;
    }

    public void setValidadeFormaPagamentoCartao(String validadeFormaPagamentoCartao) {
        this.validadeFormaPagamentoCartao = validadeFormaPagamentoCartao;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<PedidoProdutoDTO> getPedidoProdutos() {
        return pedidoProdutos;
    }

    public void setPedidoProdutos(List<PedidoProdutoDTO> pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
    }

}
