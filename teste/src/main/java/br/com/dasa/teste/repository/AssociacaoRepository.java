package br.com.dasa.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dasa.teste.model.Associacao;
import br.com.dasa.teste.model.Exame;

public interface AssociacaoRepository extends JpaRepository<Associacao, Integer>{
	
	List<Associacao> findByLaboratorio(String nome);
	
	List<Associacao> findByExame(List<Exame> exame);
}
