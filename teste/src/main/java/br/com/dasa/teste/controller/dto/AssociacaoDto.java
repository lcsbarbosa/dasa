package br.com.dasa.teste.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.dasa.teste.model.Associacao;
import br.com.dasa.teste.model.Status;
import br.com.dasa.teste.model.Tipo;

public class AssociacaoDto {
	
	private Integer id;
	private String nomeLaboratorio;
	private String nomeExame;
	private Tipo tipoExame;
	private Status statusLaboratorio;
	private Status statusExame;
	
	public Integer getId() {
		return id;
	}
	public String getNomeLaboratorio() {
		return nomeLaboratorio;
	}
	public String getNomeExame() {
		return nomeExame;
	}
	public Tipo getTipoExame() {
		return tipoExame;
	}	
	
	public Status getStatusLaboratorio() {
		return statusLaboratorio;
	}
	
	public Status getStatusExame() {
		return statusExame;
	}
		
	
	public AssociacaoDto(Associacao associacao) {
		this.id = associacao.getId();
		this.nomeLaboratorio = associacao.getLaboratorio().getNome();
		this.nomeExame = associacao.getExame().getNome();
		this.tipoExame = associacao.getExame().getTipo();
		this.statusLaboratorio = associacao.getLaboratorio().getStatus();
		this.statusExame = associacao.getExame().getStatus();
	}
	public static List<AssociacaoDto> converter(List<Associacao> associacao) {
		return associacao.stream().map(AssociacaoDto::new).collect(Collectors.toList());
	}
	
}
