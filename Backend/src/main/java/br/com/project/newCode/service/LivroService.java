package br.com.project.newCode.service;

import java.util.List;
import java.util.Optional;

import br.com.project.newCode.model.Livro;

public interface LivroService {
	
	Livro save(Livro any);
	
	Livro getByIsbn(String isbn);
	
	Livro getByTitulo(String titulo);
	
	List<Livro> findAll();

	Optional<Livro> getById(Long id);
	
	void delete(Long id);

	Livro update(Livro entity);
}
