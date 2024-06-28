package com.entrega.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MensagemEmail {

    private String emailDestinatario;

    private String assunto;

    private String corpoEmail;

}
