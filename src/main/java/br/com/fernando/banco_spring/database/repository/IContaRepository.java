package br.com.fernando.banco_spring.database.repository;

import br.com.fernando.banco_spring.database.model.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContaRepository extends JpaRepository<ContaEntity, Long> {
}
