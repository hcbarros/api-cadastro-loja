package br.com.cadastro.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cadastro.model.Cadastro;
import br.com.cadastro.model.Loja;
import br.com.cadastro.repositorio.CadastroRepositorio;


@Configuration
@Profile("prod")
public class DataLoader {
	
	@Autowired
	private CadastroRepositorio cadastroRepositorio;
	
	
	@Bean
	CommandLineRunner baseLoad() {
		 
		return args -> {
			
			Loja l1 = new Loja("abc123", "12345678", "23456789");
			Loja l2 = new Loja("bcd234", "34567896", "45678952");			
			
			Cadastro c = new Cadastro(l1);
			c = c.validarLoja(l2,null).addLoja(l2);
			
			cadastroRepositorio.save(c);					
		};
	}

}