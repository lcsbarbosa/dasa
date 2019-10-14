package br.com.dasa.teste.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dasa.teste.controller.dto.LaboratorioDto;
import br.com.dasa.teste.controller.form.AtualizacaoLaboratorioForm;
import br.com.dasa.teste.controller.form.LaboratorioForm;
import br.com.dasa.teste.model.Laboratorio;
import br.com.dasa.teste.model.Status;
import br.com.dasa.teste.repository.LaboratorioRepository;

@RestController
@RequestMapping("/rest/laboratorio")
public class LaboratorioController {
	
	//JpaRepository
	@Autowired
	LaboratorioRepository laboratorioRepository;
	
	//Metodo GET - Buscar Dados no Banco
	@GetMapping("/")
	public List<LaboratorioDto> tudo(){
		List<Laboratorio> laboratorios = laboratorioRepository.findByStatus(Status.Ativo);
		return LaboratorioDto.converter(laboratorios);
	}
	
	@GetMapping("/{nome}")
	public List<LaboratorioDto> laboratorioNome(@PathVariable("nome") final String nome){
		List<Laboratorio> laboratorios = laboratorioRepository.findByNome(nome);
		return LaboratorioDto.converter(laboratorios);
	}
	
	@GetMapping("/id/{id}")
	public LaboratorioDto laboratorioId(@PathVariable Integer id) {
		Laboratorio laboratorio = laboratorioRepository.getOne(id);
		return new LaboratorioDto(laboratorio);
	}
	
	
	//Metodo Post - Criar Dados no Banco
	@PostMapping
	public ResponseEntity<LaboratorioDto> cadastrar(@RequestBody @Valid LaboratorioForm form, UriComponentsBuilder uriBuilder) {
		Laboratorio laboratorios = form.converter();
		laboratorioRepository.save(laboratorios);
		
		URI uri = uriBuilder.path("/rest/lab/{id}").buildAndExpand(laboratorios.getId()).toUri();
		return ResponseEntity.created(uri).body(new LaboratorioDto(laboratorios));
	}
	
	
	//Metodo Put - Atualizar Dados no Banco
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<LaboratorioDto> atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizacaoLaboratorioForm form) {
		Optional<Laboratorio> optional = laboratorioRepository.findById(id);
		if (optional.isPresent()) {
			Laboratorio laboratorio = form.atualizar(id, laboratorioRepository);
			return ResponseEntity.ok(new LaboratorioDto(laboratorio));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//Metodo Delete - Deletar Dados no Banco
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Integer id) {
		Optional<Laboratorio> optional = laboratorioRepository.findById(id);
		if (optional.isPresent()) {
			laboratorioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
