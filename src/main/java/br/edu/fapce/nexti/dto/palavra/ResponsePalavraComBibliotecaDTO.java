package br.edu.fapce.nexti.dto.palavra;

import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.model.PalavrasBiblioteca;
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
public class ResponsePalavraComBibliotecaDTO {

	private @NonNull Long  id;
	private @NonNull String nome;
	private @NonNull String traducao1;
	private String traducao2;
	private @NonNull Biblioteca biblioteca; 
	
	public ResponsePalavraComBibliotecaDTO(PalavrasBiblioteca pa) {
		setId(pa.getId());
		setNome(pa.getNome());
		setTraducao1(pa.getTraducao1());
		setTraducao2(pa.getTraducao2());
		setBiblioteca(pa.getBiblioteca());
	}
}
