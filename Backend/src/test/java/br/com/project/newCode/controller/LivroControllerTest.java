package br.com.project.newCode.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.project.newCode.controller.form.LivroRequest;
import br.com.project.newCode.model.Autor;
import br.com.project.newCode.model.Categoria;
import br.com.project.newCode.model.Livro;
import br.com.project.newCode.model.LivroFisico;
import br.com.project.newCode.service.AutorService;
import br.com.project.newCode.service.CategoriaService;
import br.com.project.newCode.service.LivroService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class LivroControllerTest {

	static String LIVRO_API = "/livros";

	@Autowired
	MockMvc mvc;

	@MockBean
	AutorService autorService;

	@MockBean
	CategoriaService categoriaService;

	@MockBean
	LivroService livroService;

	@Test
	@DisplayName("Deve criar um livro com sucesso.")
	public void criaLisvroTest() throws Exception {

		Autor autor = criaAutor();
		Categoria categoria = criaCategoria();

		LivroRequest dto = criaLivroRequest();

		Livro livro = new LivroFisico("Clean Code", "Escreva um código limpo", new BigDecimal(200), 350, "212",
				dto.converterData("12/08/2006"), categoria, autor, "sedex");

		mockAutorECategoria(autor, categoria);
		BDDMockito.given(livroService.save(Mockito.any(Livro.class))).willReturn(livro);

		String json = new ObjectMapper().writeValueAsString(dto);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(LIVRO_API)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isCreated()).andExpect(jsonPath("titulo").value(dto.getTitulo()));

	}

	@Test
	@DisplayName("Deve gerar um erro de validação ao tentar criar livro sem todas as informações necessárias.")
	public void criaCategoriaInvalidoTest() throws Exception {

		String json = new ObjectMapper().writeValueAsString(new LivroRequest());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(LIVRO_API)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isBadRequest());

	}

	@Test
	@DisplayName("Deve gerar um erro de validação ao tentar criar um livro já existente.")
	public void criaLivroComIsbnDuplicadoTest() throws Exception {

		Autor autor = criaAutor();
		Categoria categoria = criaCategoria();

		LivroRequest dto = criaLivroRequest();

		String json = new ObjectMapper().writeValueAsString(dto);

		String errorMessage = "Isbn já cadastrado";

		mockAutorECategoria(autor, categoria);
		BDDMockito.given(livroService.save(Mockito.any(Livro.class))).willReturn(null);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(LIVRO_API)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isBadRequest()).andExpect(jsonPath("field").value("Isbn"))
				.andExpect(jsonPath("message").value(errorMessage));

	}

	@Test
	@DisplayName("Deve gerar um erro de validação ao tentar criar um livro já existente.")
	public void retornaListaLivrosTest() throws Exception {

		Autor autor = criaAutor();
		Categoria categoria = criaCategoria();

		LivroRequest dto = criaLivroRequest();

		List<Livro> listaLivros = new ArrayList<>();

		listaLivros.add(new LivroFisico("Clean Code", "Escreva um código limpo", new BigDecimal(200), 350, "212",
				dto.converterData("12/08/2006"), categoria, autor, "sedex"));

		mockAutorECategoria(autor, categoria);
		BDDMockito.given(livroService.findAll()).willReturn(listaLivros);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(LIVRO_API)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		mvc.perform(request).andExpect(status().isOk());

	}

	private void mockAutorECategoria(Autor autor, Categoria categoria) {
		BDDMockito.given(autorService.getByEmail(Mockito.any())).willReturn(autor);
		BDDMockito.given(categoriaService.getByNome(Mockito.any())).willReturn(categoria);
	}

	private LivroRequest criaLivroRequest() {
		return new LivroRequest("Clean Code", "Escreva um código limpo", new BigDecimal(200), 350, "212", "12/08/2006",
				"Software", "robert-martin@gmail.com", "", "sedex");
	}

	private Categoria criaCategoria() {
		return new Categoria("Software");
	}

	private Autor criaAutor() {
		return new Autor("Robert C. Martin", "robert-martin@gmail.com", "Consultor internacional de sotware");
	}

}
