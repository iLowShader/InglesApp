package br.edu.fapce.nexti.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fapce.nexti.dto.palavra.ResponsePalavraComBibliotecaDTO;
import br.edu.fapce.nexti.dto.palavra.ResponsePalavraDTO;
import br.edu.fapce.nexti.model.PalavrasBiblioteca;
import br.edu.fapce.nexti.response.ErrorResponse;
import br.edu.fapce.nexti.service.PalavraService;
import br.edu.fapce.nexti.util.GenericsUtil;

@RestController
@CrossOrigin(origins = "*")
public class PalavraController {

	private static final String PALAVRAS = "/palavras";

	private static final String PALAVRASPORBIBLIOTECA = "/palavras/{BibliotecaId}";

	private static final String SAVEPALAVRA = "/palavra";

	// private static final String SAVEPALAVRASEMBIBLIOTECA =
	// "/save_Palavra_sem_Biblioteca";

	private static final String DELETEPALAVRA = "/palavra/{PalavraId}";

	private static final String UPDATEPALAVRA = "/palavra";

	@Autowired
	private PalavraService palavraService;

	@SuppressWarnings("rawtypes")
	@GetMapping(value = PALAVRAS)
	public ResponseEntity findAll() {

		List<PalavrasBiblioteca> listaPalavras = palavraService.findAll();

		List<ResponsePalavraDTO> dtoList = new ArrayList<>();
		listaPalavras.forEach(pa -> dtoList.add(pa.toPalavraDTO()));

		return GenericsUtil.objectToResponse(dtoList);
	}

	@GetMapping(value = PALAVRASPORBIBLIOTECA)
	public ResponseEntity<?> findByBiblioteca(@PathVariable(value = "BibliotecaId") Long bibliotecaId) {
		List<PalavrasBiblioteca> listaPalavrasByBiblioteca = palavraService.findByBibliotecaId(bibliotecaId);

		List<ResponsePalavraDTO> dtoListByBiblioteca = new ArrayList<>();
		listaPalavrasByBiblioteca.forEach(pala -> dtoListByBiblioteca.add(pala.toPalavraDTO()));

		return GenericsUtil.objectToResponse(dtoListByBiblioteca);
	}

	@PostMapping(value = SAVEPALAVRA)
	public PalavrasBiblioteca saveComBiblioteca(@Valid @RequestBody ResponsePalavraComBibliotecaDTO dto) {
		return palavraService.save(dto);
	}

	// @RequestMapping(value = SAVEPALAVRASEMBIBLIOTECA, method = POST)
	// public PalavrasBiblioteca save(@Valid @RequestBody ResponsePalavraDTO dto) {
	// return palavraService.saveSemBiblioteca(dto);
	// }

	@DeleteMapping(value = DELETEPALAVRA)
	public ResponseEntity<?> delete(@PathVariable(value = "PalavraId") Long palavraId) {
		ErrorResponse<String> response = new ErrorResponse<>();
		PalavrasBiblioteca palavra = palavraService.findById(palavraId);

		if (palavra == null) {
			return ResponseEntity.badRequest().body(response);
		}

		palavraService.delete(palavraId);
		return ResponseEntity.ok(new ErrorResponse<String>());
	}

	@PutMapping(value = UPDATEPALAVRA)
	public ResponseEntity<?> update(@RequestBody PalavrasBiblioteca palavra, BindingResult result) {
		ErrorResponse<PalavrasBiblioteca> response = new ErrorResponse<>();

		try {
			validateUpdate(palavra, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErrors().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			palavra.setNome(palavra.getNome());
			palavraService.update(palavra);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	private void validateUpdate(PalavrasBiblioteca palavra, BindingResult result) {
		if (palavra.getId() == null) {
			result.addError(new ObjectError("Palavra", "Id não informado"));
			return;
		}
		if (palavra.getBiblioteca().getId() == null) {
			result.addError(new ObjectError("Palavra", "Id da Biblioteca não informado"));
			return;
		}
	}
}