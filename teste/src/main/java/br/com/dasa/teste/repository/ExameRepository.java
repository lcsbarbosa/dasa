package br.com.dasa.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dasa.teste.model.Exame;

public interface ExameRepository extends JpaRepository<Exame, Integer> {
	List<Exame> findByNome(String nome);
}
