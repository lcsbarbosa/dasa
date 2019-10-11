package br.com.dasa.teste.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.dasa.teste.model.Exame;
import br.com.dasa.teste.model.Status;
import br.com.dasa.teste.model.Tipo;

public class ExameDto {
	
	private Integer id;
	private String nome;
	private Tipo tipo;
	private Status status;
		
	//GETTERS
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public Status getStatus() {
		return status;
	}	
	
	public ExameDto(Exame exame) {
		this.id = exame.getId();
		this.nome = exame.getNome();
		this.tipo = exame.getTipo();
		this.status = exame.getStatus();
	}
	
	public static List<ExameDto> converter(List<Exame> exames) {
		return exames.stream().map(ExameDto::new).collect(Collectors.toList());
	}	
	
}
