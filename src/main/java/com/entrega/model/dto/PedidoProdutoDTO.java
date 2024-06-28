package com.entrega.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoProdutoDTO {
    @JsonProperty("idProduto")
    @NotNull(message = "O ID do Cliente não pode ser nulo.")
    long idproduto;
    @JsonProperty("Descricao")
    @NotBlank(message = "Descricao precisa ser enviado")
    String Descricao;
    @JsonProperty("quantidade")
    @NotNull(message = "quantidade do produto não pode ser nulo.")
    int quantidade;
    @JsonProperty("preco")
    @NotNull(message = "O total do pedido não pode ser nulo.")
    @Min(value = 0, message = "O total do pedido deve ser maior ou igual a zero.")
    double preco;

    public long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(long idproduto) {
        this.idproduto = idproduto;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
