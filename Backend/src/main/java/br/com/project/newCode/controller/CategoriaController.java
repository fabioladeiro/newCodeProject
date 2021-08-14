package br.com.project.newCode.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.newCode.controller.form.CategoriaRequest;
import br.com.project.newCode.model.Categoria;
import br.com.project.newCode.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private CategoriaService service;
	private ModelMapper mapper;

	public CategoriaController(CategoriaService service, ModelMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaRequest cadastrarCategoria(@RequestBody @Valid CategoriaRequest request) {

		Categoria entity = mapper.map(request, Categoria.class);
		Categoria saved = service.save(entity);
		return mapper.map(saved, CategoriaRequest.class);
	}

}
