package br.com.dasa.teste.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.dasa.teste.controller.dto.LaboratorioDto;
import br.com.dasa.teste.controller.form.AtualizacaoLaboratorioForm;
import br.com.dasa.teste.controller.form.LaboratorioForm;
import br.com.dasa.teste.model.Laboratorio;
import br.com.dasa.teste.model.Status;
import br.com.dasa.teste.repository.LaboratorioRepository;

@RestController
@Cacheable(value = "listaLaboratorio")
@RequestMapping("/rest/laboratorio")
public class LaboratorioController {
	
	// JpaRepository
	@Autowired
	LaboratorioRepository laboratorioRepository;

	// Metodo GET
	//Buscar Dados da tabela laboratorio no Banco
	@GetMapping("/")
	public Page<LaboratorioDto> lista(@RequestParam(required = false) String nome,
			@PageableDefault(sort="id", direction = Direction.ASC) Pageable paginacao) {
				
		if(nome == null) {
			Page<Laboratorio> laboratorios = laboratorioRepository.findByStatus(Status.Ativo, paginacao);
			return LaboratorioDto.converter(laboratorios);			
		} else {
			Page<Laboratorio> laboratorios = laboratorioRepository.findByNome(nome, paginacao);
			return LaboratorioDto.converter(laboratorios);
		}
	}	

	//Buscar Dados da tabela laboratorio utilizando ID
	@GetMapping("/id/{id}")
	public ResponseEntity<LaboratorioDto> laboratorioId(@PathVariable Integer id) {
		Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
		if(laboratorio.isPresent()) {
			return ResponseEntity.ok(new LaboratorioDto(laboratorio.get()));			
		}
		
		return ResponseEntity.notFound().build();
	}

	// Metodo Post - Criar Dados na tabela laboratorio
	@PostMapping
	public ResponseEntity<LaboratorioDto> cadastrar(@RequestBody @Valid LaboratorioForm form,
			UriComponentsBuilder uriBuilder) {
		Laboratorio laboratorios = form.converter();
		laboratorioRepository.save(laboratorios);

		URI uri = uriBuilder.path("/rest/lab/{id}").buildAndExpand(laboratorios.getId()).toUri();
		return ResponseEntity.created(uri).body(new LaboratorioDto(laboratorios));
	}

	// Metodo Put - Atualizar Dados na tabela laboratorio
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<LaboratorioDto> atualizar(@PathVariable Integer id,
			@RequestBody @Valid AtualizacaoLaboratorioForm form) {
		Optional<Laboratorio> optional = laboratorioRepository.findById(id);
		if (optional.isPresent()) {
			Laboratorio laboratorio = form.atualizar(id, laboratorioRepository);
			return ResponseEntity.ok(new LaboratorioDto(laboratorio));
		}

		return ResponseEntity.notFound().build();
	}
}
