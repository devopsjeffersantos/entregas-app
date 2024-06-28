package com.entrega.unitario.controller;

import com.entrega.controller.EntregaController;
import com.entrega.model.EntregaMapper;
import com.entrega.model.dto.EntregaDTO;
import com.entrega.model.entity.Entrega;
import com.entrega.service.EntregaServiceImpl;
import com.entrega.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EntregaControllerTest {

    @Mock
    private EntregaServiceImpl entregaService;

    @InjectMocks
    private EntregaController entregaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class CriarEntregasTest {
        @Nested
        class Success {

            @Test
            void deveCriarNovaEntregaComSucesso() throws IOException {
                EntregaDTO entregaDTO = new EntregaDTO();
                Entrega entregaSimulada = new Entrega();
                when(entregaService.criarEntrega(entregaDTO)).thenReturn(entregaSimulada);

                ResponseEntity<Object> response = entregaController.criarEntrega(entregaDTO);

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(entregaSimulada, response.getBody());
            }
        }

        @Nested
        class Failure{
            @Test
            void deveFalharAoCriarNovaEntrega() throws IOException {
                EntregaDTO entregaDTO = new EntregaDTO();
                when(entregaService.criarEntrega(entregaDTO)).thenThrow(new RuntimeException("Erro ao salvar entrega"));
                Exception exception = assertThrows(RuntimeException.class, () -> entregaController.criarEntrega(entregaDTO));
                assertTrue(exception.getMessage().contains("Erro ao salvar entrega"));
            }
        }
    }

    @Nested
    class AtualizarEntregasTest {
        @Nested
        class Success {
            @Test
            void deveAtualizarEntregaComSucesso() throws IOException {
                String id = "1";
                EntregaDTO entregaDTO = JsonUtil.readEntregaDTOFromJsonFile("src/main/java/com/entrega/util/PayloadEntrega.json");
                Entrega entregaSimulada = EntregaMapper.mapToEntrega(entregaDTO);
                entregaSimulada.setId(id);
                when(entregaService.getEntregaById(id)).thenReturn(Optional.of(new Entrega()));
                when(entregaService.atualizarEntrega(id, entregaDTO)).thenReturn(entregaSimulada);
                ResponseEntity<Object> response = entregaController.atualizarEntrega(id, entregaDTO);
                assertEquals(entregaSimulada, response.getBody());
                assertEquals(HttpStatus.OK, response.getStatusCode());
            }
        }
        @Nested
        class Failure{
            @Test
            void deveFalharAoAtualizarEntregaNaoEncontrada() throws IOException {
                String id = "1";
                EntregaDTO entregaDTO = new EntregaDTO();
                when(entregaService.atualizarEntrega(id, entregaDTO)).thenThrow(new RuntimeException("Erro ao atualizar entrega"));
                Exception exception = assertThrows(RuntimeException.class, () -> entregaController.atualizarEntrega(id, entregaDTO));
                assertTrue(exception.getMessage().contains("404 NOT_FOUND \"Entrega não encontrada\""));
            }
        }
    }

    @Nested
    class ExcluirEntregasTest {
        @Nested
        class Success {
            @Test
            void deveExcluirEntregaComSucesso() throws IOException {
                String id = "1";
                EntregaDTO entregaDTO = JsonUtil.readEntregaDTOFromJsonFile("src/main/java/com/entrega/util/PayloadEntrega.json");
                Entrega entregaSimulada = EntregaMapper.mapToEntrega(entregaDTO);
                when(entregaService.getEntregaById(id)).thenReturn(Optional.of(new Entrega()));
                entregaController.excluirEntrega(id);
                verify(entregaService, times(1)).excluirEntrega(id);
            }
        }

        @Nested
        class Failure {
            @Test
            void deveFalharAoDeletarEntregaNaoEncontrada() {
                String id = "1";
                when(entregaService.getEntregaById(id)).thenReturn(Optional.empty());
                ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> entregaController.excluirEntrega(id));
                assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
                assertTrue(exception.getReason().contains("Entrega não encontrada"));
                verify(entregaService, never()).excluirEntrega(id);
            }
        }
    }
}
