package br.edu.fapce.nexti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import br.edu.fapce.nexti.model.Usuario;
import br.edu.fapce.nexti.repository.UsuarioRepository;

@Controller
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String index1() {
		return "404";
	}
	
	@RequestMapping(value = "/register")
	public String cadastrar() {

		return "register";
	}

	@RequestMapping(value = "registersuccess", method = RequestMethod.POST)
	public String cadastrar(@RequestParam("email") String email, @RequestParam("senha") String senha, Model model) {

		Usuario novoUsuario = new Usuario(email, senha);

		repository.save(novoUsuario);

		return "register";
	}

	

	
}
