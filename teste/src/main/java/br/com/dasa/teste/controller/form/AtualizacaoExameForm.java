package br.com.dasa.teste.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.dasa.teste.model.Exame;
import br.com.dasa.teste.model.Status;
import br.com.dasa.teste.model.Tipo;
import br.com.dasa.teste.repository.ExameRepository;

public class AtualizacaoExameForm {
	
	@NotNull @NotEmpty @Length(max = 150)
	private String nome;
	
	@NotNull
	private Tipo tipo;
	
	@NotNull
	private Status status;
		

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

	public Exame atualizar(Integer id, ExameRepository exameRepository) {
		Exame exame = exameRepository.getOne(id);
		exame.setNome(this.nome);
		exame.setTipo(this.tipo);
		exame.setStatus(this.status);
		
		return exame;
	}	
	
}
