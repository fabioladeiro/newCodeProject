package br.com.project.newCode.service.impl;

import org.springframework.stereotype.Service;

import br.com.project.newCode.model.Autor;
import br.com.project.newCode.repository.AutorRepository;
import br.com.project.newCode.service.AutorService;

@Service
public class AutorServiceImpl implements AutorService {
	
	private AutorRepository repository;
	
	public AutorServiceImpl(AutorRepository repository) {
		this.repository = repository;
	}

	@Override
	public Autor save(Autor autor) {
		return repository.save(autor);
	}

}
