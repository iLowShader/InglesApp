package br.edu.fapce.nexti.dto.biblioteca;

import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.security.model.LoginUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ResponseBibliotecaComUsuarioDTO {

	private @NonNull Long bibliotecaid;
	private @NonNull String nome;
	private @NonNull LoginUser usuario;
	
	public ResponseBibliotecaComUsuarioDTO(Biblioteca bi) {
		setBibliotecaid(bi.getId());
		setNome(bi.getNome());
		setUsuario(bi.getUsuario());
	}
	
}
