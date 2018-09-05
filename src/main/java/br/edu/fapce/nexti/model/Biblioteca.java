package br.edu.fapce.nexti.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import br.edu.fapce.nexti.security.model.LoginUser;
import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "biblioteca")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Proxy(lazy = true)
public class Biblioteca implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "login_userid", nullable = false)
	private LoginUser usuario;

	// private Usuario usuario;

	@Column(name = "nome", nullable = false)
	private String nome;

	@OneToMany(mappedBy = "biblioteca", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PalavrasBiblioteca> palavras;

}
