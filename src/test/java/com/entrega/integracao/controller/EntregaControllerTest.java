package com.entrega.integracao.controller;

import com.entrega.controller.EntregaController;
import com.entrega.model.dto.EntregaDTO;
import com.entrega.model.entity.Entrega;
import com.entrega.repository.EntregaRepository;
import com.entrega.util.JsonUtil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EntregaControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private EntregaController entregaController;
    @Autowired
    private EntregaRepository entregaRepository;
    @Nested
    class ObterEntregasTest {
        @Nested
        class Success {
            @Test
            public void deveRetornarListaDeEntregasDoServicoRealComSucesso() {

            }
            @Test
            public void deveBuscarEntregaComIdEspecificoDoServicoRealComSucesso() {
                // ... Implementação do teste ...
            }
        }
        @Nested
        class Failure {

            @Test
            public void devePropagarExcecaoDoServicoAoObterEntregas() {

            }
            @Test
            public void deveRetornarNotFoundParaIdInexistente() {

            }

        }
    }
    @Nested
    class CriarEntregasTest {
        @Nested
        class Success {
            @Test
            public void deveCriarNovaEntregaNoServicoRealComSucesso() throws IOException {
                EntregaDTO entregaDTO = JsonUtil.readEntregaDTOFromJsonFile("src/main/java/com/entrega/util/PayloadEntrega.json");
                Entrega entrega = new Entrega();
                Mockito.when(entregaController.criarEntrega(any(EntregaDTO.class))).thenReturn(ResponseEntity.ok(entrega));
                ResponseEntity<Object> responseEntity = restTemplate.postForEntity("/api/v1/entrega/criar", entregaDTO, Object.class);
                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            }
        }
        @Nested
        class Failure {
            @Test
            public void deveFalharAoCriarNovaEntregaNoServicoReal() throws IOException {
                EntregaDTO entregaDTO = JsonUtil.readEntregaDTOFromJsonFile("src/main/java/com/entrega/util/PayloadEntrega.json");
                entregaDTO.setCliente(null);
                Mockito.when(entregaController.criarEntrega(any(EntregaDTO.class))).thenReturn(null);
                ResponseEntity<Object> responseEntity = restTemplate.postForEntity("/api/v1/entrega/criar", entregaDTO, Object.class);
                assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
            }
        }

    }
    @Nested
    class AtualizarEntregasTest {
        @Nested
        class Success {
                @Test
                public void deveAtualizarEntregaExistenteNoServicoRealComSucesso() throws IOException {
                    // ... Implementação do teste ...
                }
        }

        @Nested
        class Failure {
            @Test
            public void deveFalharAoAtualizarEntregaInexistente() throws IOException {
                // ... Implementação do teste ...
            }
        }
    }
    @Nested
    class ExcluirEntregasTest {
        @Nested
        class Success {
            @Test
            public void deveExcluirEntregaComIdEspecificoDoServicoRealComSucesso() {
                // ... Implementação do teste ...
            }
        }
        @Nested
        class Failure {
            @Test
            public void deveFalharAoExcluirEntregaComIdEspecificoDoServicoRealComSucesso() throws IOException {
                EntregaDTO entregaDTO = JsonUtil.readEntregaDTOFromJsonFile("src/main/java/com/entrega/util/PayloadEntrega.json");
                Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrada")).when(entregaController).excluirEntrega(any());
                HttpEntity<EntregaDTO> requestEntity = new HttpEntity<>(entregaDTO);
                ResponseEntity<Entrega> responseEntity = restTemplate.exchange("/api/v1/entrega/deletar/8c2810aa-743b-426e-9127-f943d0226f14", HttpMethod.DELETE, requestEntity, Entrega.class);
                assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
            }
        }
    }
    }
