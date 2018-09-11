package br.edu.fapce.nexti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fapce.nexti.dto.biblioteca.ResponseBibliotecaDTO;
import br.edu.fapce.nexti.model.PalavrasBiblioteca;

public interface PalavrasBibliotecaRepository extends JpaRepository<PalavrasBiblioteca, Long> {

	List<PalavrasBiblioteca> findByBiblioteca(ResponseBibliotecaDTO biblioteca);

	PalavrasBiblioteca findById(Long id);

	PalavrasBiblioteca findByNome(String nome);

}
