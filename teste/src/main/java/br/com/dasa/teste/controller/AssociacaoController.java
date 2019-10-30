package br.com.dasa.teste.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dasa.teste.controller.dto.AssociacaoDto;
import br.com.dasa.teste.controller.form.AssociacaoForm;
import br.com.dasa.teste.controller.form.AtualizaAssociacaoForm;
import br.com.dasa.teste.model.Associacao;
import br.com.dasa.teste.repository.AssociacaoRepository;
import br.com.dasa.teste.repository.ExameRepository;
import br.com.dasa.teste.repository.LaboratorioRepository;

@RestController
@Cacheable(value = "listaAssociacao")
@RequestMapping("/rest/associacao")
public class AssociacaoController {
	
	//JpaRepository
	@Autowired
	AssociacaoRepository associacaoRepository;
	
	@Autowired
	LaboratorioRepository laboratorioRepository;

	@Autowired
	ExameRepository exameRepository;
	
	//Metodo GET - Buscar Dados da tabela associacao no Banco
	@GetMapping("/")
	public List<AssociacaoDto> tudo(){
		List<Associacao> associacao = associacaoRepository.findAll();
		return AssociacaoDto.converter(associacao);
	}
	
	//Metodo GET - Buscar Dados da tabela associacao utilizando nome
	@GetMapping("/{nome}")
	private List<AssociacaoDto> associacaoNome(@PathVariable("nome") final String nome) {
		List<Associacao> associacao = associacaoRepository.findByLaboratorioNome(nome);
		return AssociacaoDto.converter(associacao);
	}
	
	//Metodo GET - Buscar Dados da tabela associacao utilizando ID
	@GetMapping("/id/{id}")
	private ResponseEntity<AssociacaoDto> associacaoID(@PathVariable Integer id) {
		Optional<Associacao> associacao = associacaoRepository.findById(id);
		if(associacao.isPresent()) {
			return ResponseEntity.ok(new AssociacaoDto(associacao.get()));			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//Metodo Post - Criar Dados na tabela associacao
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaAssociacao", allEntries = true)
	public ResponseEntity<AssociacaoDto> cadastrar(@RequestBody @Valid AssociacaoForm form, UriComponentsBuilder uriBuilder){
		Associacao associacao = form.converter(laboratorioRepository, exameRepository);
		if(associacao == null) {
			return ResponseEntity.notFound().build();
		}
		associacaoRepository.save(associacao);
		
		URI uri = uriBuilder.path("/rest/associacao/{id}").buildAndExpand(associacao.getId()).toUri();
		return ResponseEntity.created(uri).body(new AssociacaoDto(associacao));
	}
	
	//Metodo Put - Atualizar Dados na tabela associacao
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaAssociacao", allEntries = true)
	public ResponseEntity<AssociacaoDto> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizaAssociacaoForm form) {
		Optional<Associacao> optional = associacaoRepository.findById(id);
		if(optional.isPresent()) {
			Associacao associacao = form.atualiza(id, associacaoRepository, laboratorioRepository, exameRepository);
			if(associacao != null)
				return ResponseEntity.ok(new AssociacaoDto(associacao));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//Desassocia os Exames dos Laboratorios da Tabela Associacao
	public void desassociar(Integer id) {
		Optional<Associacao> optional = associacaoRepository.findById(id);
		if(optional.isPresent()) {
			associacaoRepository.deleteById(id);
		}
	}
}
