package com.entrega.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteDTO {
    @JsonProperty("id")
    @NotNull(message = "O ID do cliente não pode ser nulo.")
    Long id;
    @JsonProperty("nome")
    @NotBlank(message = "nome precisa ser enviado")
    String nome;
    @JsonProperty("email")
    @NotBlank(message = "email precisa ser enviado")
    String email;
    @JsonProperty("telefone")
    @NotBlank(message = "telefone precisa ser enviado")
    String telefone;
    @NotNull(message = "O endereco não pode ser nulo.")
    @Valid
    EnderecoDTO endereco;
    @JsonProperty("dataNascimento")
    @NotBlank(message = "dataNascimento precisa ser enviado")
    String dataNascimento;
    @JsonProperty("cpf")
    @NotBlank(message = "cpf precisa ser enviado")
    String cpf;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
