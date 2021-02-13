package br.com.cadastro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import br.com.cadastro.model.Cadastro;
import br.com.cadastro.model.Loja;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CadastroCepLojaApplicationTests {
	
	@LocalServerPort
	private int port;
 
 	@Autowired
	private TestRestTemplate restTemplate;

	
	@Test
	@Order(1)
	public void mainTest() {
	    CadastroCepLojaApplication.main(new String[] {});
	}
	
	@Test
	@Order(2)
	public void deveConterAStringcodigo_loja() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/cadastro/1",
				String.class)).contains("loja");
	}
	
	@Test
	@Order(3)
    public void deveInserirUmaLojaNoBanco() {
				
		Loja loja = new Loja("codigo", "50000000", "50000002");
		
		Cadastro c1 = getLojas();		
		this.restTemplate
				.postForEntity("http://localhost:" + port + "/cadastro", loja, Cadastro.class);	
		Cadastro c2 = getLojas();		
		
		assertNotEquals(c1.getLojas().size(), c2.getLojas().size());
	}
	
	@Test
	@Order(4)
    public void deveEvitarInserirUmaLojaComCodigoJaExistente() {
				
		Loja loja = new Loja("abc123", "50000100", "50000102");
		
		Cadastro c1 = getLojas();		
		this.restTemplate
				.postForEntity("http://localhost:" + port + "/cadastro", loja, Cadastro.class);	
		Cadastro c2 = getLojas();		
		
		assertEquals(c1.getLojas().size(), c2.getLojas().size());
	}
	
	@Test
	@Order(5)
    public void deveEvitarInserirUmaLojaComFaixaInicialMaiorQueFinal() {
				
		Loja loja = new Loja("abc123", "50000100", "50000099");
		
		Cadastro c1 = getLojas();		
		this.restTemplate
				.postForEntity("http://localhost:" + port + "/cadastro", loja, Cadastro.class);	
		Cadastro c2 = getLojas();		
		
		assertEquals(c1.getLojas().size(), c2.getLojas().size());
	}

	@Test
	@Order(6)
    public void deveEvitarInserirUmaLojaComFaixaDeCEPQueJaExiste() {
				
		Loja loja = new Loja("abc123", "20000000", "20000001");
		
		Cadastro c1 = getLojas();		
		this.restTemplate
				.postForEntity("http://localhost:" + port + "/cadastro", loja, Cadastro.class);	
		Cadastro c2 = getLojas();		
		
		assertEquals(c1.getLojas().size(), c2.getLojas().size());
	}
	
	@Test
	@Order(7)
    public void deveAdicionarLojaAUmCadastro() {
				
		Cadastro cadastro1 = new Cadastro();
		cadastro1.addLoja(new Loja("codigo1", "11111111", "11111112"));
		
		Cadastro cadastro2 = new Cadastro();
		
		assertNotEquals(cadastro1.getLojas().size(), cadastro2.getLojas().size());
	}
	
	@Test
	@Order(8)
    public void deveBuscarLojaPorId() {
				
		Loja loja = getLoja("2");		
		assertEquals(loja.getCodigo_loja(), "abc123");
	}
	
	@Test
	@Order(9)
    public void deveBuscarLojaPorCodigo() {
				
		Loja loja = getLoja("/codigo/abc123");		
		assertEquals(loja.getCodigo_loja(), "abc123");
	}
	
	@Test
	@Order(10)
    public void deveBuscarLojaPorFaixaCEP() {
				
		Loja loja = getLoja("/cep/40000000");		
		assertEquals(loja.getCodigo_loja(), "bcd234");
	}	
		
	@Test
	@Order(11)
    public void deveAlterarFaixaCEPDeLoja() {
				
		Loja loja = getLoja("3");		
		String faixaFinal_1 = loja.getFaixa_fim();
		loja.setFaixa_fim("49999999");
		
		this.restTemplate
		.put("http://localhost:" + port + "/cadastro/3", loja);	
		
		String faixaFinal_2 = getLoja("3").getFaixa_fim();
		
		assertNotEquals(faixaFinal_1, faixaFinal_2);
	}
	
	@Test
	@Order(12)
    public void deveDeletarLojaPorId() {
			
		Cadastro c1 = getLojas();
					
		this.restTemplate
			.delete("http://localhost:" + port + "/cadastro/2");
		
		Cadastro c2 = getLojas();
		
		assertNotEquals(c1.getLojas().size(), c2.getLojas().size());
	}

		
	private Cadastro getLojas() {

		return this.restTemplate
				.getForEntity("http://localhost:" + port + "/cadastro", Cadastro.class)
				.getBody();		 
	}
	
	private Loja getLoja(String text) {
		
		return this.restTemplate
				.getForEntity("http://localhost:" + port + "/cadastro/"+text, Loja.class)
				.getBody();		 
	}
	
}
