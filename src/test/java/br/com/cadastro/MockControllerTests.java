package br.com.cadastro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertTrue; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cadastro.controller.CadastroController;
import br.com.cadastro.model.Loja;
import br.com.cadastro.service.CadastroService;


@TestMethodOrder(OrderAnnotation.class)
@WebMvcTest(controllers = CadastroController.class)
class MockControllerTests {
		
	@MockBean
	private CadastroService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mvc;
	
	
	@Test
	@Order(13)
	public void deveCadastrarUmaLoja() throws Exception {
	
		Loja loja = new Loja("loja1","50000005","50000006"); 
		
		String l = objectMapper.writeValueAsString(loja);
		
		mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/cadastro/")
				.contentType("application/json")
				.content(l))
			.andExpect(MockMvcResultMatchers.status().isCreated());	
	}
	
	@Test
	@Order(14)
	public void deveAlterarUmaLoja() throws Exception {
	
		Loja loja = new Loja("novoCodigo","01111111","02222222"); 
		loja.setId(2L);
		
		String l = objectMapper.writeValueAsString(loja);
		
		mvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/cadastro/2")
				.contentType("application/json")
				.content(l))
			.andExpect(MockMvcResultMatchers.status().isOk());	
	}
		
	@Order(15)
	@Test
	public void deveBuscarTodasAsLojas() throws Exception {		
		getLojas("");
	}
	
	@Order(16)
	@Test
	public void deveBuscarLojaPorId() throws Exception {		
		getLojas("3");
	}
	
	@Order(17)
	@Test
	public void deveBuscarLojaPorFaixaDeCEP() throws Exception {		
		getLojas("cep/40000000");
	}
	
	@Order(18)
	@Test
	public void deveBuscarLojaPorCodigo() throws Exception {		
		getLojas("codigo/abc123");
	}
	
	@Order(19)
	@Test
	public void deveDeletarLojaPorId() throws Exception {
		delete("2");		
	}
	
	private void getLojas(String texto) throws Exception  {
		
		mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/cadastro/"+texto)
				.contentType("application/json")
				.accept("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	public void delete(String text) throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/cadastro/"+text)
				.contentType("application/json")
				.accept("application/json"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());	
	}
	
}
