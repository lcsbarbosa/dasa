package br.com.dasa.teste.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@Cacheable(value = "listaExame")
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
	public Page<ExameDto> tudo(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable paginacao){		
		
		if(nome == null) {
			Page<Exame> exames = exameRepository.findByStatus(Status.Ativo, paginacao);
			return ExameDto.converter(exames);
		} else {
			Page<Exame> exames = exameRepository.findByNome(nome, paginacao);
			return ExameDto.converter(exames);
		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<ExameDto> exameId(@PathVariable Integer id) {
		Optional<Exame> exames = exameRepository.findById(id);
		if(exames.isPresent()) {
			return ResponseEntity.ok(new ExameDto(exames.get()));			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/*
	@GetMapping("/laboratorios/{nome}")
	private List<LaboratorioDto> associacaoNome(@PathVariable("nome") final String nome) {
		return LaboratorioDto.converter(laboratorios);
	}*/
	
	//Metodo Post - Criar Dados no Banco
	@PostMapping
	@CacheEvict(value = "listaExame", allEntries = true)
	public ResponseEntity<ExameDto> cadastrar(@RequestBody @Valid ExameForm form, UriComponentsBuilder uriBuilder) {
		Exame exames = form.converter();
		exameRepository.save(exames);
		
		URI uri = uriBuilder.path("/rest/exame/{id}").buildAndExpand(exames.getId()).toUri();
		return ResponseEntity.created(uri).body(new ExameDto(exames));
	}
	
	//Metodo Put - Atualizar Dados no Banco
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaExame", allEntries = true)
	public ResponseEntity<ExameDto> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizacaoExameForm form) {
		Optional<Exame> optional = exameRepository.findById(id);
		if (optional.isPresent()) {
			Exame Exame = form.atualizar(id, exameRepository);
			return ResponseEntity.ok(new ExameDto(Exame));
		}
		
		return ResponseEntity.notFound().build();
	}	
}
