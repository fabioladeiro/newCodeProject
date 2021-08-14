package br.com.project.newCode.service;

import java.util.Optional;

import br.com.project.newCode.model.Livro;

public interface LivroService {
	
	Livro save(Livro any);

	Optional<Livro> getById(Long id);
	
	void delete(Long id);

	Livro update(Livro entity);
}
