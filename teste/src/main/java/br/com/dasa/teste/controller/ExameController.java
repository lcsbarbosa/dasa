package br.com.dasa.teste.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dasa.teste.controller.dto.ExameDto;
import br.com.dasa.teste.controller.form.AtualizacaoExameForm;
import br.com.dasa.teste.controller.form.ExameForm;
import br.com.dasa.teste.model.Exame;
import br.com.dasa.teste.model.Status;
import br.com.dasa.teste.repository.AssociacaoRepository;
import br.com.dasa.teste.repository.ExameRepository;
import br.com.dasa.teste.repository.LaboratorioRepository;

@RestController
@RequestMapping("/rest/exame")
public class ExameController {
	
	//JpaRepository
	@Autowired
	ExameRepository exameRepository;
	
	@Autowired
	AssociacaoRepository associacaoRepository;
	
	@Autowired
	LaboratorioRepository laboratorioRepository;
	
	//Metodo GET - Buscar Dados no Banco
	@GetMapping("/")
	public List<ExameDto> tudo(){
		List<Exame> exames = exameRepository.findByStatus(Status.Ativo);
		return ExameDto.converter(exames);
	}
	
	@GetMapping("/{nome}")
	public List<ExameDto> exameNome(@PathVariable("nome") final String nome){
		List<Exame> exames = exameRepository.findByNome(nome);
		return ExameDto.converter(exames);
	}
	
	@GetMapping("/id/{id}")
	public ExameDto exameId(@PathVariable Integer id) {
		Exame exames = exameRepository.getOne(id);
		return new ExameDto(exames);
	}
	
	/*
	@GetMapping("/laboratorios/{nome}")
	private List<LaboratorioDto> associacaoNome(@PathVariable("nome") final String nome) {
		return LaboratorioDto.converter(laboratorios);
	}*/
	
	//Metodo Post - Criar Dados no Banco
	@PostMapping
	public ResponseEntity<ExameDto> cadastrar(@RequestBody @Valid ExameForm form, UriComponentsBuilder uriBuilder) {
		Exame exames = form.converter();
		exameRepository.save(exames);
		
		URI uri = uriBuilder.path("/rest/exame/{id}").buildAndExpand(exames.getId()).toUri();
		return ResponseEntity.created(uri).body(new ExameDto(exames));
	}
	
	//Metodo Put - Atualizar Dados no Banco
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ExameDto> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizacaoExameForm form) {
		Optional<Exame> optional = exameRepository.findById(id);
		if (optional.isPresent()) {
			Exame Exame = form.atualizar(id, exameRepository);
			return ResponseEntity.ok(new ExameDto(Exame));
		}
		
		return ResponseEntity.notFound().build();
	}	
}
