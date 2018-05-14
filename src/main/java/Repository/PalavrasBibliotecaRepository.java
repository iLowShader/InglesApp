package Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.edu.fapce.nexti.api.entities.Biblioteca;
import br.edu.fapce.nexti.api.entities.PalavrasBiblioteca;

public interface PalavrasBibliotecaRepository extends CrudRepository<PalavrasBiblioteca, Long>{
	
	List<PalavrasBiblioteca> findByBiblioteca (Biblioteca biblioteca);
	
}
