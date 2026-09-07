package br.com.fernando.banco_spring.database.repository;

import br.com.fernando.banco_spring.database.model.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransacaoRepository extends JpaRepository<TransacaoEntity, Long> {
}
