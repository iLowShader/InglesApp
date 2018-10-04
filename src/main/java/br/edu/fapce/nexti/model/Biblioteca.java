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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.fapce.nexti.dto.biblioteca.ResponseBibliotecaDTO;
import br.edu.fapce.nexti.security.model.LoginUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
// @Proxy(lazy = true)
public class Biblioteca implements Serializable {

	private static final long serialVersionUID = -4911355131744430193L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "login_user_id")
	private LoginUser usuario;

	@Column(name = "nome", nullable = false)
	private String nome;

	@OneToMany(mappedBy = "biblioteca", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PalavrasBiblioteca> palavras;

	public ResponseBibliotecaDTO toBibliotecaDTO() {
		return new ResponseBibliotecaDTO(this);
	}
}
