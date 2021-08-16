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

import br.com.project.newCode.controller.form.CategoriaRequest;
import br.com.project.newCode.model.Categoria;
import br.com.project.newCode.service.AutorService;
import br.com.project.newCode.service.CategoriaService;
import br.com.project.newCode.service.LivroService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class CategoriaControllerTest {

	static String CATEGORIA_API = "/categorias";

	@Autowired
	MockMvc mvc;

	@MockBean
	AutorService autorService;

	@MockBean
	CategoriaService categoriaService;

	@MockBean
	LivroService livroService;

	@Test
	@DisplayName("Deve criar uma categoria com sucesso.")
	public void criaCategoriaTest() throws Exception {

		CategoriaRequest dto = criaCategoriaRequest();
		Categoria savedcategoria = new Categoria(1L, "Software");

		BDDMockito.given(categoriaService.save(Mockito.any(Categoria.class))).willReturn(savedcategoria);

		String json = new ObjectMapper().writeValueAsString(dto);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(CATEGORIA_API)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isCreated()).andExpect(jsonPath("nome").value(dto.getNome()));

	}

	@Test
	@DisplayName("Deve gerar um erro de validação ao tentar criar categoria sem todas as informações necessárias.")
	public void criaCategoriaInvalidoTest() throws Exception {

		String json = new ObjectMapper().writeValueAsString(new CategoriaRequest());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(CATEGORIA_API)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isBadRequest());

	}

	@Test
	@DisplayName("Deve gerar um erro de validação ao tentar criar uma categoria já existente.")
	public void criaCategoriaComNomeDuplicadoTest() throws Exception {

		CategoriaRequest dto = criaCategoriaRequest();

		String json = new ObjectMapper().writeValueAsString(dto);

		String errorMessage = "Categoria já cadastrada";

		BDDMockito.given(categoriaService.save(Mockito.any(Categoria.class))).willReturn(null);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(CATEGORIA_API)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json);

		mvc.perform(request).andExpect(status().isBadRequest()).andExpect(jsonPath("field").value("nome"))
				.andExpect(jsonPath("message").value(errorMessage));

	}

	private CategoriaRequest criaCategoriaRequest() {
		return new CategoriaRequest("Software");
	}

}
