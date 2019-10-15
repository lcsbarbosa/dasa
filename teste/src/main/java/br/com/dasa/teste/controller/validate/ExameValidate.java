package br.com.dasa.teste.controller.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dasa.teste.controller.dto.ExameDto;
import br.com.dasa.teste.controller.dto.LaboratorioDto;
import br.com.dasa.teste.model.Associacao;
import br.com.dasa.teste.model.Exame;
import br.com.dasa.teste.model.Laboratorio;
import br.com.dasa.teste.repository.AssociacaoRepository;
import br.com.dasa.teste.repository.LaboratorioRepository;
import javassist.expr.NewArray;

public class ExameValidate {
	
	@Autowired
	LaboratorioRepository laboratorioRepository;
	
	@Autowired
	AssociacaoRepository associacaoRepository;
	
	public List<Associacao> buscaAssociacao(List<Exame> exame){
		List<Associacao> busca = new ArrayList<Associacao>();
		List<Associacao> associacao = new ArrayList<Associacao>();
		
		for(Exame e : exame) {
			busca.add(associacaoRepository.getOne(e.getId()));
		}
		
		for(Associacao b : busca) {
			String nome = associacaoRepository.getOne(b.getId()).getLaboratorio().getNome();
			associacao.add(associacaoRepository.findByLaboratorioNome(nome).get(0));
		}
		
		return associacao;
	}	
	
}
