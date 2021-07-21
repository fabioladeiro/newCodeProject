package br.com.project.newCode.controller.form;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import br.com.project.newCode.model.Categoria;

public class CategoriaRequest {
	
	@NotBlank
	@Column(unique = true)
	private String nome;
	
	public CategoriaRequest(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Categoria toModel() {
		Categoria categoria = new Categoria(this.nome);
		return categoria;
	}
	

}
