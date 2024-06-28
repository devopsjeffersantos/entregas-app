package com.entrega.repository;



import com.entrega.model.entity.Entrega;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface EntregaRepository extends CrudRepository<Entrega, String> {
}
