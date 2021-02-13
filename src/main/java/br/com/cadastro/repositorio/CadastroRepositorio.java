package br.com.cadastro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cadastro.model.Cadastro;

public interface CadastroRepositorio extends JpaRepository<Cadastro, Long> {


}
