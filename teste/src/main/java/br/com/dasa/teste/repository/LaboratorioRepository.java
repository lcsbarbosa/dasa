package br.com.dasa.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dasa.teste.model.Associacao;
import br.com.dasa.teste.model.Laboratorio;
import br.com.dasa.teste.model.Status;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {
	
	List<Laboratorio> findByNome(String nome);

	List<Laboratorio> findByStatus(Status status);

	List<Laboratorio> findByAssociacao(List<Associacao> associacao);
}
