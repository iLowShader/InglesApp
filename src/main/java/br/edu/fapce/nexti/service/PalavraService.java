package br.edu.fapce.nexti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.model.PalavrasBiblioteca;
import br.edu.fapce.nexti.repository.PalavrasBibliotecaRepository;

@Service
public class PalavraService {

	@Autowired
	private PalavrasBibliotecaRepository palavrasRepository;

	public List<PalavrasBiblioteca> findAll() {
		return palavrasRepository.findAll();
	}

	public List<PalavrasBiblioteca> findByBiblioteca(Biblioteca biblioteca) {
		return palavrasRepository.findByBiblioteca(biblioteca);
	}

	public PalavrasBiblioteca findById(Long id) {
		return palavrasRepository.findById(id);
	}

	public PalavrasBiblioteca findByNome(String nome) {
		return palavrasRepository.findByNome(nome);
	}

	public PalavrasBiblioteca save(PalavrasBiblioteca palavra) {
		return palavrasRepository.save(palavra);
	}
}
