package br.com.project.newCode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.newCode.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Categoria getByNome(String nome);

}
