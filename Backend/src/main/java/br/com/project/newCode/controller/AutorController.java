package br.com.project.newCode.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.newCode.controller.form.AutorRequest;
import br.com.project.newCode.model.Autor;
import br.com.project.newCode.service.AutorService;

@CrossOrigin
@RestController
@RequestMapping("/autores")
public class AutorController {

	private AutorService service;
	private ModelMapper mapper;
	
	public AutorController(AutorService service, ModelMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AutorRequest cadastrarAutor(@RequestBody @Valid AutorRequest request) {
		Autor entity = mapper.map(request, Autor.class);
		Autor saved = service.save(entity);
		return mapper.map(saved, AutorRequest.class);
	}
}
	