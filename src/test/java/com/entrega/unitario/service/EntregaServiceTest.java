package com.entrega.unitario.service;

import com.entrega.model.EntregaMapper;
import com.entrega.model.dto.EntregaDTO;
import com.entrega.model.entity.Entrega;
import com.entrega.repository.EntregaRepository;
import com.entrega.service.EntregaServiceImpl;
import com.entrega.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EntregaServiceTest {
    @Mock
    private EntregaRepository entregaRepository;

    @InjectMocks
    private EntregaServiceImpl entregaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Nested
    class ObterEntregasTest {
        @Nested
        class Success {
            @Test
            void deveRetornarListaDeEntregasDoComSucesso() {
                List<Entrega> entregas = Collections.singletonList(new Entrega());
                when(entregaRepository.findAll()).thenReturn(entregas);
                Iterable<Entrega> result = entregaService.getEntregas();
                assertNotNull(result);
                assertEquals(entregas, result);
            }

            @Test
            void deveBuscarEntregaComIdEspecificoDoServicoRealComSucesso() {
                String id = "1";
                Entrega entrega = new Entrega();
                entrega.setId(id);
                Optional<Entrega> optionalEntrega = Optional.of(entrega);
                when(entregaRepository.findById(id)).thenReturn(optionalEntrega);
                Optional<Entrega> result = entregaService.getEntregaById(id);
                assertTrue(result.isPresent());
                assertEquals(entrega, result.get());
            }
        }

        @Nested
        class Failure {
            @Test
            void devePropagarExcecaoDoServicoAoObterEntregas() {
                when(entregaRepository.findAll()).thenReturn(Collections.emptyList());
                Iterable<Entrega> result = entregaService.getEntregas();
                assertTrue(((List<?>) result).isEmpty());
            }

            @Test
            void deveRetornarNotFoundParaIdInexistente() {
                String id = "1";
                Optional<Entrega> optionalEntrega = Optional.empty();
                when(entregaRepository.findById(id)).thenReturn(optionalEntrega);
                Optional<Entrega> result = entregaService.getEntregaById(id);
                assertFalse(result.isPresent());
            }

        }
    }

    @Nested
    class CriarEntregasTest {
        @Nested
        class Success {

            @Test
            void deveCriarNovaEntregaComSucesso() throws IOException {
                EntregaDTO entregaDTO = JsonUtil.readEntregaDTOFromJsonFile("src/main/java/com/entrega/util/PayloadEntrega.json");
                Entrega entregaSimulada = EntregaMapper.mapToEntrega(entregaDTO);
                when(entregaRepository.save(any(Entrega.class))).thenReturn(entregaSimulada);
                Entrega result = entregaService.criarEntrega(entregaDTO);
                assertNotNull(result);
                assertEquals(entregaDTO.getDataPedido(), result.getDataPedido());
                verify(entregaRepository, times(1)).save(any(Entrega.class));
            }

        }
        @Nested
        class Failure{
            @Test
            void deveFalharAoCriarNovaEntrega() throws IOException {
                EntregaDTO entregaDTO = JsonUtil.readEntregaDTOFromJsonFile("src/main/java/com/entrega/util/PayloadEntrega.json");
                when(entregaRepository.save(any(Entrega.class))).thenThrow(new RuntimeException("Erro ao salvar entrega"));
                Exception exception = assertThrows(RuntimeException.class, () -> entregaService.criarEntrega(entregaDTO));
                assertTrue(exception.getMessage().contains("Erro ao salvar entrega"));
                verify(entregaRepository, times(1)).save(any(Entrega.class));
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
                Entrega entrega = entregaService.atualizarEntrega(id, entregaDTO);
                assertEquals(entregaSimulada, entrega);
            }
        }
        @Nested
        class Failure{
            @Test
            void deveFalharAoAtualizarEntregaNaoEncontrada() throws IOException {
                String id = "1";
                EntregaDTO entregaDTO = new EntregaDTO(); // Aqui você deve criar um objeto EntregaDTO válido para o teste
                when(entregaRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
                NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> entregaService.atualizarEntrega(id, entregaDTO));
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
                entregaSimulada.setId(id);
                when(entregaRepository.findById(id)).thenReturn(Optional.of(entregaSimulada));
                entregaService.excluirEntrega(id);
                verify(entregaRepository, times(1)).deleteById(id);
            }
        }

        @Nested
        class Failure {
            @Test
            void deveFalharAoDeletarEntregaNaoEncontrada() {

            }
        }
    }
}
