package com.entrega.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoDTO {
    @JsonProperty("logradouro")
    @NotBlank(message = "logradouro precisa ser enviado")
    String logradouro;
    @JsonProperty("bairro")
    @NotBlank(message = "bairro precisa ser enviado")
    String bairro;
    @JsonProperty("estado")
    @NotBlank(message = "estado precisa ser enviado")
    String estado;
    @JsonProperty("cidade")
    @NotBlank(message = "cidade precisa ser enviado")
    String cidade;
    @JsonProperty("cep")
    @NotBlank(message = "cep precisa ser enviado")
    String cep;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
