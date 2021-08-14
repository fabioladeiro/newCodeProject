package br.com.project.newCode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.newCode.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
