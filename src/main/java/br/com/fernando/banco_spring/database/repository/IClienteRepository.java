package br.com.fernando.banco_spring.database.repository;

import br.com.fernando.banco_spring.database.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
