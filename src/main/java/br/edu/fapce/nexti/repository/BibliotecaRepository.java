package br.edu.fapce.nexti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.security.model.LoginUser;

@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

	Biblioteca findByNome(String nome);

	Biblioteca findById(Long id);

	Biblioteca findByUsuario(LoginUser usuario);

	List<Biblioteca> findAllByUsuario(LoginUser usuario);

}
