package br.edu.fapce.nexti.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import Repository.BibliotecaRepository;
import Repository.PalavrasBibliotecaRepository;
import br.edu.fapce.nexti.api.entities.Biblioteca;
import br.edu.fapce.nexti.api.entities.PalavrasBiblioteca;

@Controller
public class UsuarioController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@Autowired
	private BibliotecaRepository repository;
	
	@Autowired
	private PalavrasBibliotecaRepository repository2;

	@RequestMapping("dashboard")
	public String biblioteca(Model model) {
		Iterable<Biblioteca> bibliotecas = repository.findAll();

		model.addAttribute("bibliotecas", bibliotecas);

		return "dashboard";
	}

	@RequestMapping("open-library")
	public String listaPalavras(Model model, Biblioteca biblioteca) {

		Iterable<PalavrasBiblioteca> palavras = repository2.findByBiblioteca(biblioteca);

		model.addAttribute("palavras", palavras);

		return "open-library";
	}
}
