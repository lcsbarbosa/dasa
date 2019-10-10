package br.com.dasa.teste.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.dasa.teste.model.Laboratorio;
import br.com.dasa.teste.model.Status;

public class LaboratorioDto {

	private Integer id;	
	private String nome;
	private String endereco;
	private Status status;
	
	//Getters
	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public Status getStatus() {
		return status;
	}
	
	public LaboratorioDto(Laboratorio laboratorio) {
		this.id = laboratorio.getId();
		this.nome = laboratorio.getNome();
		this.endereco = laboratorio.getEndereco();
		this.status = laboratorio.getStatus();
	}

	public static List<LaboratorioDto> converter(List<Laboratorio> laboratorios) {
		return laboratorios.stream().map(LaboratorioDto::new).collect(Collectors.toList());
	}
}
