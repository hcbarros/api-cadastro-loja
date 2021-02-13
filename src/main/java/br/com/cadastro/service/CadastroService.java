package br.com.cadastro.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.model.Cadastro;
import br.com.cadastro.model.Loja;
import br.com.cadastro.repositorio.CadastroRepositorio;
import br.com.cadastro.repositorio.LojaRepositorio;


@Service
public class CadastroService {

	@Autowired
	private CadastroRepositorio cadastroRepositorio;
			
	@Autowired
	private LojaRepositorio lojaRepositorio;
	
	public Cadastro salvar(Loja loja) {
		
		if(lojaRepositorio.existsByCodigoLoja(loja.getCodigo_loja())) {
			throw new RuntimeException("Uma loja com este código já existe!");
	    }
		Cadastro cadastro = buscarLojas().validarLoja(loja,null)
										 .addLoja(loja);
		return cadastroRepositorio.save(cadastro);
	}	
	
	public Cadastro editar(Long id, Loja loja) {		
		
		Loja l = buscarLojaPorId(id);
		Cadastro cadastro = buscarLojas();
		cadastro = cadastro.validarLoja(loja, l.getCodigo_loja());
		l.setCodigo_loja(loja.getCodigo_loja());
		l.setFaixa_inicio(loja.getFaixa_inicio());
		l.setFaixa_fim(loja.getFaixa_fim());		
		
		return cadastroRepositorio.save(cadastro);
	}	
	
	public Cadastro buscarLojas() {
		return cadastroRepositorio
				.findById(1L)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	public Loja buscarLojaPorId(Long id) {
		return lojaRepositorio
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	public Loja buscarLojaPorCodigo(String codigo) {
	
		return lojaRepositorio
				.findByCodigoLoja(codigo)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	public Loja buscarLojaPeloCEP(String cep) {
		return lojaRepositorio
				.buscarPorCEP(cep)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	public void delete(Long id) {
		lojaRepositorio.deleteById(id);
	}
	
}
