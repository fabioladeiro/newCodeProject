package br.com.project.newCode.service.impl;

import br.com.project.newCode.model.Categoria;
import br.com.project.newCode.repository.CategoriaRepository;
import br.com.project.newCode.service.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaRepository repository;
	
	public CategoriaServiceImpl(CategoriaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Categoria save(Categoria categoria) {
		return repository.save(categoria);
	}

}
