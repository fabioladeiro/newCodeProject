package br.com.project.newCode.service;

import br.com.project.newCode.model.Categoria;

public interface CategoriaService {
	
	Categoria save(Categoria any);

	Categoria getByNome(String nome);
}
