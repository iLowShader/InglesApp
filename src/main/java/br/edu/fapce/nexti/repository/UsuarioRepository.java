package br.edu.fapce.nexti.repository;

import org.springframework.data.repository.CrudRepository;
import br.edu.fapce.nexti.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Usuario findByEmail(String email);
}
