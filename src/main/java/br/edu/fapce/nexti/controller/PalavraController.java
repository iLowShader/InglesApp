package br.edu.fapce.nexti.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fapce.nexti.dto.palavra.ResponsePalavraDTO;
import br.edu.fapce.nexti.model.PalavrasBiblioteca;
import br.edu.fapce.nexti.service.PalavraService;
import br.edu.fapce.nexti.util.GenericsUtil;

@RestController
@CrossOrigin(origins = "*")
public class PalavraController {

	private static final String PALAVRAS = "/v2/palavras";

	@Autowired
	private PalavraService palavraService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = PALAVRAS, method = GET)
	public ResponseEntity findAll() {

		List<PalavrasBiblioteca> listaPalavras = palavraService.findAll();

		List<ResponsePalavraDTO> dtoList = new ArrayList<>();
		listaPalavras.forEach(pa -> dtoList.add(pa.toPalavraDTO()));

		return GenericsUtil.objectToResponse(dtoList);
	}

	@RequestMapping(value = "/palavrasByBiblioteca/{BibliotecaId}", method = GET)
	public ResponseEntity findByBiblioteca(@PathVariable(value = "BibliotecaId") Long bibliotecaId) {
		List<PalavrasBiblioteca> listaPalavrasByBiblioteca = palavraService.findByBibliotecaId(bibliotecaId);

		List<ResponsePalavraDTO> dtoListByBiblioteca = new ArrayList<>();
		listaPalavrasByBiblioteca.forEach(pala -> dtoListByBiblioteca.add(pala.toPalavraDTO()));

		return GenericsUtil.objectToResponse(dtoListByBiblioteca);
	}

}