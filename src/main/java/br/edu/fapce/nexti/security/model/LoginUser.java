package br.edu.fapce.nexti.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.fapce.nexti.security.dto.loginuser.ResponseLoginUserDTO;
import br.edu.fapce.nexti.security.dto.loginuser.ResponseLoginUserDTOToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loginuser")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
// @Proxy(lazy = false)
public class LoginUser implements Serializable {

	private static final long serialVersionUID = -4911355131744430193L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "senha", nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "userrole", nullable = false)
	private UserRole userRole;

	@Column(name = "nome", nullable = false)
	private String nome;

	// @OneToMany(mappedBy="usuario", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	// private List<Biblioteca> biblioteca;

	public ResponseLoginUserDTO toResponseLoginUserDTO() {
		return new ResponseLoginUserDTO(this);
	}

	public ResponseLoginUserDTOToken toResponseLoginUserDTOToken(String token) {
		return new ResponseLoginUserDTOToken(this, token);
	}
}
