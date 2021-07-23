package br.com.project.newCode.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.newCode.controller.form.AutorRequest;
import br.com.project.newCode.model.Autor;
import br.com.project.newCode.repository.AutorRepository;

@CrossOrigin
@RestController
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorRepository autorRepository;
	
	@PostMapping
	public ResponseEntity<Autor> cadastrarAutor(@RequestBody @Valid AutorRequest request) {
		Autor autor = request.toModel();
		autorRepository.save(autor);
		return ResponseEntity.status(HttpStatus.CREATED).body(autor);
	}
}
	