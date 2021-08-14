package br.com.project.newCode.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.newCode.controller.form.LivroRequest;
import br.com.project.newCode.model.Livro;
import br.com.project.newCode.model.LivroDigital;
import br.com.project.newCode.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
	
	private ModelMapper mapper;
	private LivroService service;
	
	public LivroController(ModelMapper mapper, LivroService service) {
		this.mapper = mapper;
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LivroRequest cadastrarLivro(@RequestBody @Valid LivroRequest request) {
		Livro entity;
		if(request.getTipoEntrega().isBlank()) {
			entity = mapper.map(request, LivroDigital.class);
		} else {
			entity = mapper.map(request, LivroDigital.class);
		}
		Livro saved = service.save(entity);
		return mapper.map(saved, LivroRequest.class);
	}

}
