package br.com.dasa.teste.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.dasa.teste.model.Associacao;
import br.com.dasa.teste.model.Exame;
import br.com.dasa.teste.model.Laboratorio;
import br.com.dasa.teste.repository.AssociacaoRepository;
import br.com.dasa.teste.repository.ExameRepository;
import br.com.dasa.teste.repository.LaboratorioRepository;

public class AtualizaAssociacaoForm {

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
	
	
	public Associacao atualiza(Integer id, AssociacaoRepository associacaoRepository, LaboratorioRepository laboratorioRepository,
			ExameRepository exameRepository) {
		List<Laboratorio> laboratorios = laboratorioRepository.findByNome(laboratorio);
		List<Exame> exames = exameRepository.findByNome(exame);
		Associacao associacao = associacaoRepository.getOne(id);
		
		if(laboratorios.size() != 0 && exames.size() !=0) {			
			if(laboratorios.get(0).getStatus().toString() == "Ativo" && exames.get(0).getStatus().toString() == "Ativo") {
				associacao.setLaboratorio(laboratorios.get(0));
				associacao.setExame(exames.get(0));
				return associacao;
			}
		}
		
		return null;
	}
	
	
}
