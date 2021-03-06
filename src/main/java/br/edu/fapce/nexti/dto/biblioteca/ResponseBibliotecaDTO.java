package br.edu.fapce.nexti.dto.biblioteca;

import br.edu.fapce.nexti.model.Biblioteca;
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
public class ResponseBibliotecaDTO {

	private @NonNull Long bibliotecaid;
	private @NonNull String nome;
	
	public ResponseBibliotecaDTO(Biblioteca bi) {
		setBibliotecaid(bi.getId());
		setNome(bi.getNome());
	}
	
}
