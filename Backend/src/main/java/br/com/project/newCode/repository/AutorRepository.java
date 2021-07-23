package br.com.project.newCode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.newCode.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
