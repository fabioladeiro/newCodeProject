package br.com.project.newCode.service;

import br.com.project.newCode.model.Autor;

public interface AutorService {

	Autor save(Autor any);
	
	Autor getByEmail(String any);

}
