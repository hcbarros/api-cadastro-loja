package br.com.cadastro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Loja {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull(message = "Informe o código da loja!")
	@Column(name ="CODIGO_LOJA")
	private String codigoLoja;
	
	@Column(name ="FAIXA_INICIO")
	@NotNull(message = "Informe a faixa inicial do CEP!")	
	@Pattern(regexp = "^[0-9]{8}$",message="A faixa inicial do CEP deve conter 8 digitos!")
	private String faixa_inicio;
	
	@Column(name ="FAIXA_FIM")
	@NotNull(message = "Informe a faixa final do CEP!")
	@Pattern(regexp = "^[0-9]{8}$",message="A faixa final do CEP deve conter 8 digitos!")
	private String faixa_fim;
	
	public Loja() {
		
	}
	
	public Loja(String codigoLoja, String faixa_inicio, String faixa_fim) {
		
		this.codigoLoja = codigoLoja;
		this.faixa_inicio = faixa_inicio;
		this.faixa_fim = faixa_fim;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo_loja() {
		return codigoLoja;
	}

	public void setCodigo_loja(String codigo_loja) {
		this.codigoLoja = codigo_loja;
	}

	public String getFaixa_inicio() {
		return faixa_inicio;
	}

	public void setFaixa_inicio(String faixa_inicio) {
		this.faixa_inicio = faixa_inicio;
	}

	public String getFaixa_fim() {
		return faixa_fim;
	}

	public void setFaixa_fim(String faixa_fim) {
		this.faixa_fim = faixa_fim;
	}
	
	
	
}
