package br.com.cadastro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.model.Cadastro;
import br.com.cadastro.model.Loja;
import br.com.cadastro.service.CadastroService;


@RestController
@Validated
@RequestMapping("/cadastro")
public class CadastroController  {

	@Autowired
	private CadastroService service;

	
	@GetMapping(value = "{id}")
	public Loja buscarLojaPorId(@PathVariable Long id) {
		return service.buscarLojaPorId(id);		
	}

	@GetMapping(value = "cep/{cep}")
	public Loja buscarLojaPeloCEP(@PathVariable String cep) {
		return service.buscarLojaPeloCEP(cep);		
	}
	
	@GetMapping(value = "codigo/{codigo}")
	public Loja buscarLojaPorCodigo(@PathVariable String codigo) {
		return service.buscarLojaPorCodigo(codigo);		
	}
	
	@GetMapping
	public Cadastro buscarLojas() {
		return service.buscarLojas();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cadastro salvar(@RequestBody @Valid Loja loja) {
		return service.salvar(loja);
	}
	
	@PutMapping(value = "{id}")
	public Cadastro editar(@PathVariable Long id, @RequestBody @Valid Loja loja) {
		return service.editar(id, loja);
	}
	
	@DeleteMapping(value = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		service.delete(id);
	}
	
}

