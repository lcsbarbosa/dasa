package br.com.dasa.teste.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.dasa.teste.model.Laboratorio;

public class LaboratorioForm {

	@NotNull @NotEmpty @Length(max = 150)
	private String nome;
	
	@NotNull @NotEmpty @Length(max = 250)
	private String endereco;
	
	private String status;	
	
	
	//GETTERS e SETTERS
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	//Retorno EndPoint
	public Laboratorio converter() {
		return new Laboratorio(nome , endereco);
	}
}
