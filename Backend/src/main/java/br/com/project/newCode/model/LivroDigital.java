package br.com.project.newCode.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class LivroDigital extends Livro {

	@NotBlank
	private String dispositivo;
	
	@Deprecated
	public LivroDigital() {}

	public LivroDigital(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
			@NotNull @Min(10) BigDecimal preco, @NotNull int numeroPaginas, @NotBlank String isbn,
			Categoria categoria, Autor autor,
			@NotBlank String dispositivo) {
		super(titulo, resumo, preco, numeroPaginas, isbn, categoria, autor);
		this.dispositivo = dispositivo;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	@Override
	public String toString() {
		return "LivroDigital [dispositivo=" + dispositivo + "]";
	}  

}
