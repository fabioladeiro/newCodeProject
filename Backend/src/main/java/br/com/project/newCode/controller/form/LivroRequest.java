package br.com.project.newCode.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.project.newCode.model.Autor;
import br.com.project.newCode.model.Categoria;

public class LivroRequest {

	@NotBlank
	protected String titulo;
	@NotBlank
	@Size(max = 500)
	protected String resumo;
	@NotBlank
	@Min(10)
	protected BigDecimal preco;
	@NotBlank
	protected int numeroPaginas;
	@NotBlank
	protected String isbn;
	@NotBlank
	protected LocalDate dataPublicacao;
	@NotBlank
	protected Categoria categoria;
	@NotBlank
	protected Autor autor;
	private String dispositivo;
	private String tipoEntrega;

	public LivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
			@NotBlank @Min(10) BigDecimal preco, @NotBlank int numeroPaginas, @NotBlank String isbn,
			@NotBlank LocalDate dataPublicacao, @NotBlank Categoria categoria, @NotBlank Autor autor,
			String dispositivo, String tipoEntrega) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.categoria = categoria;
		this.autor = autor;
		this.dispositivo = dispositivo;
		this.tipoEntrega = tipoEntrega;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

}
