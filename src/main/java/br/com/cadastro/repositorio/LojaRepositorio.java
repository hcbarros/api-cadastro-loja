package br.com.cadastro.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cadastro.model.Cadastro;
import br.com.cadastro.model.Loja;

public interface LojaRepositorio extends JpaRepository<Loja, Long> {

	boolean existsByCodigoLoja(String codigo);
	
	Optional<Loja> findByCodigoLoja(String codigo);
	
	@Query(value = "SELECT * FROM Loja WHERE CAST(FAIXA_INICIO AS BIGINT) <= :cep and CAST(FAIXA_FIM AS BIGINT) >= :cep", nativeQuery = true)	
	Optional<Loja> buscarPorCEP(@Param("cep") String cep);
}
