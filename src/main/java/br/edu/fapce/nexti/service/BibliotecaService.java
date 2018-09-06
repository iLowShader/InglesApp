package br.edu.fapce.nexti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.repository.BibliotecaRepository;
import br.edu.fapce.nexti.security.model.LoginUser;

@Service
public class BibliotecaService {

	@Autowired
	private BibliotecaRepository bibliotecaRepository;
	//
	// @Autowired
	// private LoginUserService usuarioService;

	public List<Biblioteca> findAll() {
		return bibliotecaRepository.findAll();
	}

	// public List<Biblioteca> findByUsuarioId(Long usuarioId){
	// LoginUser usuario = usuarioService.findById(usuarioId);
	//
	// ***Missing return***
	// }

	public Biblioteca findByNome(String nome) {
		return bibliotecaRepository.findByNome(nome);
	}

	public Biblioteca findById(Long id) {
		return bibliotecaRepository.findById(id);
	}

	public Biblioteca findByUsuario(LoginUser usuario) {
		return bibliotecaRepository.findByUsuario(usuario);
	}

}
