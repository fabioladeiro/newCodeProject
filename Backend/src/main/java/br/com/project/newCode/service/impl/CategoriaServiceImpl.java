package br.com.project.newCode.service.impl;

import org.springframework.stereotype.Service;

import br.com.project.newCode.model.Categoria;
import br.com.project.newCode.repository.CategoriaRepository;
import br.com.project.newCode.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaRepository repository;

	public CategoriaServiceImpl(CategoriaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Categoria save(Categoria categoria) {
		Categoria findBynome = repository.getByNome(categoria.getNome());
		if (findBynome != null) {
			return null;
		}
		return repository.save(categoria);
	}

	@Override
	public Categoria getByNome(String nome) {
		return repository.getByNome(nome);
	}

}
