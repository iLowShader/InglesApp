package br.edu.fapce.nexti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.security.model.LoginUser;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

	Biblioteca findByNome(String nome);

	Biblioteca findById(Long id);

	Biblioteca findByUsuario(LoginUser usuario);
	
	List<Biblioteca> findByUsuarioId(Long usuarioId);

}
