package br.edu.fapce.nexti.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fapce.nexti.dto.biblioteca.ResponseBibliotecaDTO;
import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.security.model.LoginUser;
import br.edu.fapce.nexti.service.BibliotecaService;
import br.edu.fapce.nexti.util.GenericsUtil;

@RestController
@CrossOrigin(origins = "*")
public class BibliotecaController {

	private static final String BIBLIOTECA = "/v2/bibliotecas";

	private static final String BIBLIOTECASUSUARIO = "/v2/bibliotecasAndUsuario";

	@Autowired
	private BibliotecaService bibliotecaService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = BIBLIOTECA, method = GET)
	public ResponseEntity findAll() {

		List<Biblioteca> listaBibliotecas = bibliotecaService.findAll();

		List<ResponseBibliotecaDTO> dtoList = new ArrayList<>();
		listaBibliotecas.forEach(bi -> dtoList.add(bi.toBibliotecaDTO()));

		return GenericsUtil.objectToResponse(dtoList);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = BIBLIOTECASUSUARIO, method = POST)
	public ResponseEntity findByUsuario(@Valid @RequestBody LoginUser usuario) {

		List<Biblioteca> listaBibliotecasByUsuario = bibliotecaService.findAllByUsuario(usuario);

		List<ResponseBibliotecaDTO> dtoListByUsuario = new ArrayList<>();
		listaBibliotecasByUsuario.forEach(bi -> dtoListByUsuario.add(bi.toBibliotecaDTO()));

		return GenericsUtil.objectToResponse(dtoListByUsuario);
	}

}
