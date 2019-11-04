package br.com.dasa.teste.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dasa.teste.model.Exame;
import br.com.dasa.teste.model.Status;

public interface ExameRepository extends JpaRepository<Exame, Integer> {
	
	List<Exame> findByNome(String nome);
	
	Page<Exame> findByNome(String nome, Pageable paginacao);

	Page<Exame> findByStatus(Status ativo, Pageable paginacao);
}
