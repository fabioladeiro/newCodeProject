package br.com.project.newCode.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class LivroFisico extends Livro {

	@NotBlank
	private String tipoEntrega;

	public LivroFisico(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
			@NotBlank @Min(10) BigDecimal preco, @NotBlank int numeroPaginas, @NotBlank String isbn,
			@NotBlank LocalDate dataPublicacao, @NotBlank Categoria categoria, Autor autor,
			@NotBlank String tipoEntrega) {
		super(titulo, resumo, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
		this.tipoEntrega = tipoEntrega;
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

}
