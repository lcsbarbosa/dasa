package br.com.dasa.teste.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.dasa.teste.model.Associacao;
import br.com.dasa.teste.model.Exame;
import br.com.dasa.teste.model.Laboratorio;
import br.com.dasa.teste.repository.ExameRepository;
import br.com.dasa.teste.repository.LaboratorioRepository;

public class AssociacaoForm {
	
	@NotNull @NotEmpty
	private String laboratorio;
	@NotNull @NotEmpty
	private String exame;
	
	
	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = exame;
	}


	public Associacao converter(LaboratorioRepository laboratorioRepository, ExameRepository exameRepository) {
		List<Laboratorio> laboratorios = laboratorioRepository.findByNome(laboratorio);
		List<Exame> exames = exameRepository.findByNome(exame);
		
		if(laboratorios.size() != 0 && exames.size() !=0) {
			Laboratorio laboratorio = laboratorios.get(0);
			Exame exame = exames.get(0);
			
			if(laboratorios.get(0).getStatus().toString() == "Ativo" && exames.get(0).getStatus().toString() == "Ativo")
				return new Associacao(laboratorio,exame);
		}
		
		return null;		
	}
}
