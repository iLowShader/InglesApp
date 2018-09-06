package br.edu.fapce.nexti.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fapce.nexti.security.model.LoginUserService;

@RestController
@CrossOrigin(origins = "*")
public class BibliotecaController {

	private static final String BIBLIOTECA = "/v2/bibliotecas";
	
	@Autowired
	private LoginUserService loginUserService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = BIBLIOTECA, method= GET)
	public ResponseEntity findAll() {
		
	}
}
