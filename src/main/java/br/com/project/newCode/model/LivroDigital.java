package br.com.project.newCode.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class LivroDigital extends Livro {

	@NotBlank
	private String dispositivo;

	public LivroDigital(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
			@NotBlank @Min(10) BigDecimal preco, @NotBlank int numeroPaginas, @NotBlank String isbn,
			@NotBlank LocalDate dataPublicacao, @NotBlank Categoria categoria, Autor autor,
			@NotBlank String dispositivo) {
		super(titulo, resumo, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
		this.dispositivo = dispositivo;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

}
