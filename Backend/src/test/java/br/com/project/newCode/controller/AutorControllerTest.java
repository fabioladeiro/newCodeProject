package br.com.project.newCode.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import br.com.project.newCode.controller.form.AutorRequest;
import br.com.project.newCode.model.Autor;
import br.com.project.newCode.service.AutorService;
import br.com.project.newCode.service.CategoriaService;
import br.com.project.newCode.service.LivroService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class AutorControllerTest {

	static String AUTOR_API = "/autores";

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
	public void criaAutorTest() throws Exception {

		AutorRequest dto = criaAutorRequest();
		Autor savedAutor = new Autor(1L, "Robert C. Martin", "robert-martin@gmail.com",
				"Consultor internacional de sotware");

		BDDMockito.given(autorService.save(Mockito.any(Autor.class))).willReturn(savedAutor);

		String json = new ObjectMapper().writeValueAsString(dto);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(AUTOR_API)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isCreated()).andExpect(jsonPath("nome").value(dto.getNome()))
				.andExpect(jsonPath("email").value(dto.getEmail()))
				.andExpect(jsonPath("descricao").value(dto.getDescricao()));

	}

	@Test
	@DisplayName("Deve gerar um erro de validação ao tentar criar autor sem todas as informações necessárias.")
	public void criaAutorInvalidoTest() throws Exception {

		String json = new ObjectMapper().writeValueAsString(new AutorRequest());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(AUTOR_API)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isBadRequest());

	}

	@Test
	@DisplayName("Deve gerar um erro de validação ao tentar criar um autor já existente.")
	public void criaAutorComEmailDuplicadoTest() throws Exception {

		AutorRequest dto = criaAutorRequest();

		String json = new ObjectMapper().writeValueAsString(dto);

		String errorMessage = "Email já cadastrado";

		BDDMockito.given(autorService.save(Mockito.any(Autor.class))).willReturn(null);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(AUTOR_API)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isBadRequest()).andExpect(jsonPath("field").value("email"))
				.andExpect(jsonPath("message").value(errorMessage));

	}
	
	private AutorRequest criaAutorRequest() {
		return new AutorRequest("Robert C. Martin", "robert-martin@gmail.com",
				"Consultor internacional de sotware");
	}

}
