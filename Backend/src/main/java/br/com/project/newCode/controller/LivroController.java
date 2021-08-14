package br.com.project.newCode.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.newCode.controller.form.LivroRequest;
import br.com.project.newCode.exception.FormErrorDto;
import br.com.project.newCode.model.Autor;
import br.com.project.newCode.model.Categoria;
import br.com.project.newCode.model.Livro;
import br.com.project.newCode.service.AutorService;
import br.com.project.newCode.service.CategoriaService;
import br.com.project.newCode.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	private ModelMapper mapper;
	private LivroService service;
	private AutorService autorService;
	private CategoriaService categoriaService;

	public LivroController(ModelMapper mapper, LivroService service, AutorService autorService,
			CategoriaService categoriaService) {
		this.mapper = mapper;
		this.service = service;
		this.autorService = autorService;
		this.categoriaService = categoriaService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Object cadastrarLivro(@RequestBody @Valid LivroRequest request) {
		Autor autorEncontradoPeloNome = autorService.getByEmail(request.getAutor());
		Categoria categoriaEncontradaPeloNome = categoriaService.getByNome(request.getCategoria());
		
		if (autorEncontradoPeloNome == null) {
			return ResponseEntity.badRequest().body(new FormErrorDto("autor", "Autor não está cadastrado"));
		}
		if (categoriaEncontradaPeloNome == null) {
			return ResponseEntity.badRequest().body(new FormErrorDto("categoria", "Categoria não está cadastrada"));
		}

		Livro entity;
		if (request.getTipoEntrega().isBlank()) {
			entity = request.toModelLivroDigital(categoriaEncontradaPeloNome, autorEncontradoPeloNome);
		} else {
			entity = request.toModelLivroFisico(categoriaEncontradaPeloNome, autorEncontradoPeloNome);
		}
		Livro saved = service.save(entity);
		if (saved == null) {
			return ResponseEntity.badRequest().body(new FormErrorDto("Isbn", "Isbn já cadastrado"));
		}
		return mapper.map(saved, LivroRequest.class);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Livro> retornarListaLivros() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Object retornaDetalheLivro(@PathVariable Long id) {
		Optional<Livro> livroEncontrado = service.getById(id);
		System.out.println(livroEncontrado);
		if(livroEncontrado.isEmpty()) {
			return ResponseEntity.notFound().build();
		} return livroEncontrado;
	}
	
	@DeleteMapping("{id}")
	public Object deletaLivro(@PathVariable Long id) { 
		Optional<Livro> livroEncontrado = service.getById(id);
		if(livroEncontrado.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	

}
