package br.edu.fapce.nexti.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Repository.BibliotecaRepository;
import Repository.PalavrasBibliotecaRepository;
import Repository.UsuarioRepository;
import br.edu.fapce.nexti.api.entities.Biblioteca;
import br.edu.fapce.nexti.api.entities.PalavrasBiblioteca;
import br.edu.fapce.nexti.api.entities.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private BibliotecaRepository repository;

	@Autowired
	private PalavrasBibliotecaRepository repository2;

	@Autowired
	private UsuarioRepository repository3;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "cadastrar", method = RequestMethod.POST)
	public String cadastrar(@RequestParam("email") String email, @RequestParam("senha") String senha, Model model) {

		Usuario novoUsuario = new Usuario(email, senha);

		repository3.save(novoUsuario);

		return "index";
	}

	@RequestMapping("dashboard")
	public String biblioteca(Model model) {
		Iterable<Biblioteca> bibliotecas = repository.findAll();

		model.addAttribute("bibliotecas", bibliotecas);

		return "dashboard";
	}

	@RequestMapping("biblioteca/{nome}")
	public String listaPalavras(Model model, Biblioteca biblioteca) {

		Iterable<PalavrasBiblioteca> palavras = repository2.findByBiblioteca(biblioteca);

		model.addAttribute("palavras", palavras);

		return "open-library";
	}
}
