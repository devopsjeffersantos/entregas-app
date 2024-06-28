package com.entrega.controller;

import com.entrega.model.dto.EntregaDTO;
import com.entrega.model.entity.Entrega;
import com.entrega.service.EntregaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController{

    private final EntregaServiceImpl entregaService;

    @Autowired
    public EntregaController(EntregaServiceImpl entregaService) {
        this.entregaService = entregaService;
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de entregas encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Entrega.class))),
        @ApiResponse(responseCode = "204", description = "Nenhuma entrega encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })


    @GetMapping("/obter/tudo")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_USER')")
    public ResponseEntity<Iterable<Entrega>> getEntregas() {
        Iterable<Entrega> entregas = entregaService.getEntregas();
        if (entregas.iterator().hasNext()) {
            return ResponseEntity.ok(entregas);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma Entrega Encontrada");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrega encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Entrega.class))),
            @ApiResponse(responseCode = "404", description = "Entrega não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })

    @GetMapping("/obter/porId/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_USER')")
    public ResponseEntity<Optional<Entrega>> getEntregaById(@PathVariable String id) {
        Optional<Entrega> entrega = entregaService.getEntregaById(id);
        if (entrega.isPresent()) {
            return ResponseEntity.ok(entrega);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrada");
        }
    }

    @Operation(summary = "Cria uma nova entrega", responses = {
            @ApiResponse(responseCode = "201", description = "Entrega criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação ou regra de negócio"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> criarEntrega(@RequestBody @Valid EntregaDTO entregaDTO) {
        Entrega entrega = entregaService.criarEntrega(entregaDTO);
        return ResponseEntity.ok(entrega);
    }

    @Operation(summary = "Atualiza uma entrega existente", responses = {
            @ApiResponse(responseCode = "200", description = "Entrega atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entrega não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados da entrega inválidos")
    })
    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> atualizarEntrega(@PathVariable String id, @RequestBody EntregaDTO entregaDTO) {
        Optional<Entrega> entrega = entregaService.getEntregaById(id);
        if (entrega.isPresent()) {
            return ResponseEntity.ok(entregaService.atualizarEntrega(id, entregaDTO));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrada");
        }
    }

    @Operation(summary = "Exclui uma entrega", responses = {
            @ApiResponse(responseCode = "204", description = "Entrega excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    })
    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void excluirEntrega(@PathVariable String id) {
        Optional<Entrega> entrega = entregaService.getEntregaById(id);
        if (entrega.isPresent()) {
            entregaService.excluirEntrega(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrada");
        }
    }

}
