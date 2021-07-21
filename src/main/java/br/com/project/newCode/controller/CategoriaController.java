package br.com.project.newCode.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.newCode.controller.form.CategoriaRequest;
import br.com.project.newCode.model.Categoria;
import br.com.project.newCode.repository.CategoriaRepository;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody @Valid CategoriaRequest request) {
		Categoria categoria = request.toModel();
		categoriaRepository.save(categoria);
		return ResponseEntity.ok(categoria);
	}

}
