package br.edu.fapce.nexti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.fapce.nexti.model.Usuario;
import br.edu.fapce.nexti.service.SecurityService;
import br.edu.fapce.nexti.service.UserService;
import br.edu.fapce.nexti.validator.UserValidator;


@Controller
public class UsuarioController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
    private UserValidator userValidator;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("formularioUsuario", new Usuario());
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method =RequestMethod.POST)
	public String cadastro(@ModelAttribute("formularioUsuario") Usuario formularioUsuario, BindingResult bidingResult, Model model) {
		userValidator.validate(formularioUsuario, bidingResult);
		
		if(bidingResult.hasErrors()) {
			return "registration";
		}
		
		userService.save(formularioUsuario);
		
		securityService.autologin(formularioUsuario.getEmail(), formularioUsuario.getSenha());
		
		return "redirect:/welcome";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

		
		return "login";
	}
	
	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "welcome";
	}
	
}
