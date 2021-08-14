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
	public Autor save(Autor autor){
		if(getByEmail(autor.getEmail()) != null) {
			return null;
		}
		return repository.save(autor);			
	}
	
	@Override
	public Autor getByEmail(String email) {
		return repository.getByEmail(email);
	}

}
