package br.com.project.newCode.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.project.newCode.model.Autor;
import br.com.project.newCode.model.Categoria;
import br.com.project.newCode.model.LivroDigital;
import br.com.project.newCode.model.LivroFisico;

public class LivroRequest {

	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String resumo;
	@NotNull
	@Min(10)
	private BigDecimal preco;
	@NotNull
	private int numeroPaginas;
	@NotBlank
	private String isbn;
	@NotBlank
	private String dataPublicacao;
	@NotBlank
	private String categoria;
	@NotBlank
	@Email
	private String emailAutor;
	private String dispositivo;
	private String tipoEntrega;

	public LivroRequest() {
	}

	public LivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
			@NotNull @Min(10) BigDecimal preco, @NotNull int numeroPaginas, @NotBlank String isbn,
			@NotBlank String dataPublicacao, @NotBlank String categoria, @NotBlank String emailAutor,
			String dispositivo, String tipoEntrega) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.categoria = categoria;
		this.emailAutor = emailAutor;
		this.dispositivo = dispositivo;
		this.tipoEntrega = tipoEntrega;
		this.dataPublicacao = dataPublicacao;
	}

	public LocalDate converterData(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(data, formatter);
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

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getAutor() {
		return emailAutor;
	}

	public void setAutor(String autor) {
		this.emailAutor = autor;
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

	public LivroFisico toModelLivroFisico(Categoria categoriaEncontradaPeloNome, Autor autorEncontradoPeloNome) {
		LocalDate dataPublicacaoConvertida = converterData(this.dataPublicacao);

		return new LivroFisico(this.getTitulo(), this.getResumo(), this.getPreco(), this.getNumeroPaginas(),
				this.getIsbn(), dataPublicacaoConvertida, categoriaEncontradaPeloNome, autorEncontradoPeloNome,
				this.getTipoEntrega());
	}

	public LivroDigital toModelLivroDigital(Categoria categoriaEncontradaPeloNome, Autor autorEncontradoPeloNome) {
		LocalDate dataPublicacaoConvertida = converterData(this.dataPublicacao);
		return new LivroDigital(this.getTitulo(), this.getResumo(), this.getPreco(), this.getNumeroPaginas(),
				this.getIsbn(), dataPublicacaoConvertida, categoriaEncontradaPeloNome, autorEncontradoPeloNome,
				this.getDispositivo());
	}

}
