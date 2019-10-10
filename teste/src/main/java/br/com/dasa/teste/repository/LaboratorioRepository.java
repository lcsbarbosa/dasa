package br.com.dasa.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dasa.teste.model.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {
	List<Laboratorio> findByNome(String nome);
}
