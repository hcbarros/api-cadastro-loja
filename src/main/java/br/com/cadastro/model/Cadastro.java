package br.com.cadastro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Cadastro {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "loja_id")
	private List<Loja> lojas;
	
	public Cadastro() {
		if(lojas == null) lojas = new ArrayList<>();
	}
	
	public Cadastro(Loja loja) {
		if(lojas == null) lojas = new ArrayList<>();
		
		validarLoja(loja, null);
		addLoja(loja);	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}	

	public Cadastro validarLoja(Loja loja, String codigoExistente) {
		
		int count = 0;
		
		long fInicio = Long.parseLong(loja.getFaixa_inicio());
		long fFim = Long.parseLong(loja.getFaixa_fim());		
		for(Loja l: lojas) {
			
			long fInicioLista = Long.parseLong(l.getFaixa_inicio());
			long fFimLista = Long.parseLong(l.getFaixa_fim());

			if(codigoExistente != null && loja.getCodigo_loja().equals(l.getCodigo_loja())) {
				if(!loja.getCodigo_loja().equals(codigoExistente))
				throw new RuntimeException("Uma loja com este código já existe!");
			}			
			
			if((fInicio >= fInicioLista && fInicio <= fFimLista) || 
					(fFim >= fInicioLista && fFim <= fFimLista)) {
				if(codigoExistente != l.getCodigo_loja())
				throw new RuntimeException("A faixa de CEP ja existe no cadastro");			
			}
		}
				
		if(fInicio > fFim) {
			throw new RuntimeException("A faixa de inicio nao deve ser maior que a faixa final!");
		}		
		
		return this;
	}
	
	public Cadastro addLoja(Loja loja) {
						
		this.lojas.add(loja);
		return this;
	}

}
