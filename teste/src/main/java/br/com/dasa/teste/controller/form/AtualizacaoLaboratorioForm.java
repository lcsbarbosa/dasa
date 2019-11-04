package br.com.dasa.teste.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.dasa.teste.model.Laboratorio;
import br.com.dasa.teste.model.Status;
import br.com.dasa.teste.repository.LaboratorioRepository;

public class AtualizacaoLaboratorioForm {
	
	@NotNull @NotEmpty @Length(max = 250)
	private String endereco;
	
	@NotNull
	private Status status;	
	

	//GETTERS e SETTERS
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Laboratorio atualizar(Integer id, LaboratorioRepository laboratorioRepository) {
		Laboratorio laboratorio = laboratorioRepository.getOne(id);
		laboratorio.setEndereco(this.endereco);
		laboratorio.setStatus(this.status);
		
		return laboratorio;
	}
	
	
}
