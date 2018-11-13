package br.edu.fapce.nexti.security.dto.token;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

	@NotEmpty(message = "o campo email não pode ser vazio.")
	private String email;
	@NotEmpty(message = "o campo password não pode ser vazio.")
	private String password;

}
