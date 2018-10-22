package br.edu.fapce.nexti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fapce.nexti.model.Biblioteca;

@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

	Biblioteca findByNome(String nome);

	Biblioteca findById(Long id);

//	List<Biblioteca> findAllByEmail(String email);
	
	List<Biblioteca> findByUsuarioId(Long id);

}
