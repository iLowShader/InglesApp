package br.edu.fapce.nexti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fapce.nexti.model.PalavrasBiblioteca;

@Repository
public interface PalavrasBibliotecaRepository extends JpaRepository<PalavrasBiblioteca, Long> {

	List<PalavrasBiblioteca> findByBibliotecaId(Long id);

	Optional<PalavrasBiblioteca> findById(Long id);

	PalavrasBiblioteca findByNome(String nome);

}
