package com.entrega.service;

import com.entrega.model.EntregaMapper;
import com.entrega.model.dto.EntregaDTO;
import com.entrega.model.entity.Entrega;
import com.entrega.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntregaServiceImpl {
    private EntregaRepository entregaRepository;
    @Autowired
    public EntregaServiceImpl(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }
    public Iterable<Entrega> getEntregas() {
        Iterable<Entrega> entregas = entregaRepository.findAll();
        return entregas;
    }
    public Optional<Entrega> getEntregaById(String id) {
        Optional<Entrega> entrega = entregaRepository.findById(id);
        return entrega;
    }
    public Entrega criarEntrega(EntregaDTO entregaDTO) {
        Entrega entrega = EntregaMapper.mapToEntrega(entregaDTO);
        return entregaRepository.save(entrega);
    }
    public Entrega atualizarEntrega(String id, EntregaDTO entregaDTO) {

        Entrega entrega = EntregaMapper.mapToEntrega(entregaDTO);

        Optional<Entrega> entregaOptional = entregaRepository.findById(id);
        Entrega existingEntrega = entregaOptional.get();
        existingEntrega.setIdPedido(entrega.getIdPedido());
        existingEntrega.setCliente(entrega.getCliente());
        existingEntrega.setDataPedido(entrega.getDataPedido());
        existingEntrega.setFormaPagamento(entrega.getFormaPagamento());
        existingEntrega.setPedidoProdutos(entrega.getPedidoProdutos());
        existingEntrega.setTotalPedido(entrega.getTotalPedido());
        return entregaRepository.save(existingEntrega);

    }
    public void excluirEntrega(String id) {
        Optional<Entrega> entrega = entregaRepository.findById(id);
        entregaRepository.deleteById(id);
    }
}
