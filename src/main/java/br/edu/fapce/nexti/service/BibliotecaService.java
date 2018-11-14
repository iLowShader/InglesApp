package br.edu.fapce.nexti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fapce.nexti.dto.biblioteca.ResponseBibliotecaComUsuarioDTO;
import br.edu.fapce.nexti.dto.biblioteca.ResponseBibliotecaDTO;
import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.repository.BibliotecaRepository;

@Service
public class BibliotecaService {

	@Autowired
	private BibliotecaRepository bibliotecaRepository;

	public List<Biblioteca> findAll() {
		return bibliotecaRepository.findAll();
	}

	// public List<Biblioteca> findAllByEmail(String email) {
	// return bibliotecaRepository.findAllByEmail(email);
	// }

	public Biblioteca findByNome(String nome) {
		return bibliotecaRepository.findByNome(nome);
	}

	public Biblioteca findById(Long id) {
		return bibliotecaRepository.findById(id);
	}

	public Biblioteca save(ResponseBibliotecaComUsuarioDTO bibliotecaDTO) {
		Biblioteca biblioteca = bibliotecaFromBibliotecaDTO(bibliotecaDTO);
		return bibliotecaRepository.save(biblioteca);
	}

	public Biblioteca saveSemUsuario(ResponseBibliotecaDTO bibliotecaDTO) {
		Biblioteca biblioteca = bibliotecaSemUsuarioFromBibliotecaDTO(bibliotecaDTO);
		return bibliotecaRepository.save(biblioteca);
	}

	public Biblioteca update(Biblioteca biblioteca) {
		return bibliotecaRepository.save(biblioteca);
	}

	public List<Biblioteca> findByUserId(Long id) {
		return bibliotecaRepository.findByUsuarioId(id);
	}

	public Biblioteca bibliotecaFromBibliotecaDTO(ResponseBibliotecaComUsuarioDTO bibliotecaDto) {
		return Biblioteca.builder().nome(bibliotecaDto.getNome()).usuario(bibliotecaDto.getUsuario()).build();
	}

	public Biblioteca bibliotecaSemUsuarioFromBibliotecaDTO(ResponseBibliotecaDTO bibliotecaDto) {
		return Biblioteca.builder().nome(bibliotecaDto.getNome()).build();
	}

	public void delete(Long id) {
		bibliotecaRepository.delete(id);
	}

}
