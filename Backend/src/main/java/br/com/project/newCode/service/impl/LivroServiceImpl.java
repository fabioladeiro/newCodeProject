package br.com.project.newCode.service.impl;

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
		return repository.save(livro);
	}

	@Override
	public Optional<Livro> getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Livro update(Livro entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
