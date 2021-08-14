package br.com.project.newCode.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class LivroFisico extends Livro {

	@NotBlank
	private String tipoEntrega;
	
	@Deprecated
	public LivroFisico() {}

	public LivroFisico(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
			@NotNull @Min(10) BigDecimal preco, @NotNull int numeroPaginas, @NotBlank String isbn,
			Categoria categoria, Autor autor, @NotBlank String tipoEntrega) {
		super(titulo, resumo, preco, numeroPaginas, isbn, categoria, autor);
		this.tipoEntrega = tipoEntrega;
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

}
