package br.com.dasa.teste.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dasa.teste.model.Laboratorio;
import br.com.dasa.teste.model.Status;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {

	List<Laboratorio> findByNome(String nome);
	
	Page<Laboratorio> findByNome(String nome, Pageable paginacao);

	Page<Laboratorio> findByStatus(Status status, Pageable paginacao);
}
