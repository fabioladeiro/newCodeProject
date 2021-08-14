package br.com.project.newCode.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.project.newCode.model.Livro;
import br.com.project.newCode.repository.LivroRepository;
import br.com.project.newCode.service.LivroService;

@Service
public class LivroServiceImpl implements LivroService {
	
	private LivroRepository repository;
	
	public LivroServiceImpl(LivroRepository repository) {
		this.repository = repository;
	}


	@Override
	public Livro save(Livro livro) {
		if(getByIsbn(livro.getIsbn()) != null || getByTitulo(livro.getTitulo()) != null) {
			return null;
		}
		return repository.save(livro);
	}

	@Override
	public Optional<Livro> getById(Long id) {
		return repository.findById(id);
		
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public Livro update(Livro entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Livro getByIsbn(String isbn) {
		return repository.getByIsbn(isbn);
	}


	@Override
	public Livro getByTitulo(String titulo) {
		return repository.getByTitulo(titulo);
	}


	@Override
	public List<Livro> findAll() {
		return repository.findAll();
	}

}
