package br.edu.fapce.nexti.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import br.edu.fapce.nexti.dto.palavra.ResponsePalavraDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "palavras_biblioteca")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Proxy(lazy = true)
public class PalavrasBiblioteca implements Serializable {

	private static final long serialVersionUID = -4911355131744430193L;
	@ManyToOne(fetch = FetchType.EAGER)
	private Biblioteca biblioteca;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "traducao1", nullable = false)
	private String traducao1;

	@Column(name = "traducao2", nullable = true)
	private String traducao2;

	public ResponsePalavraDTO toPalavraDTO() {
		return new ResponsePalavraDTO(this);
	}
	
	public void a() {
		
	}
}
