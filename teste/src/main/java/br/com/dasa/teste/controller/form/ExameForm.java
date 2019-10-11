package br.com.dasa.teste.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.dasa.teste.model.Exame;
import br.com.dasa.teste.model.Tipo;

public class ExameForm {
	
	@NotNull @NotEmpty @Length(max = 150)
	private String nome;
	private Tipo tipo;	
	
	
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
	
	
	public Exame converter() {
		return new Exame(nome, tipo);
	}	
}
