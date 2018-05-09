package br.edu.fapce.nexti.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "palavras_biblioteca")
public class PalavrasBiblioteca implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Biblioteca biblioteca;
	private String nome;
  private String traducao1;
  private String traducao2;
  private String traducao3;
	
	public PalavrasBiblioteca() {
	}
  
	@ManyToOne
	public Biblioteca getBiblioteca() {
		return biblioteca;
	}
	
	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	
  @Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "traducao1", nullable = false)
	public String getTraducao1() {
		return traducao1;
	}

	public void setTraducao1(String traducao1) {
		this.traducao1 = traducao1;
	}
  
  @Column(name = "traducao2", nullable = true)
	public String getTraducao2() {
		return traducao2;
	}

	public void setTraducao2(String traducao2) {
		this.traducao2 = traducao2;
	}
  
  @Column(name = "traducao3", nullable = true)
	public String getTraducao3() {
		return traducao3;
	}

	public void setTraducao3(String traducao3) {
		this.traducao3 = traducao3;
	}

}
