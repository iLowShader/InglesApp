package br.edu.fapce.nexti.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import br.edu.fapce.nexti.dto.biblioteca.ResponseBibliotecaComUsuarioDTO;
import br.edu.fapce.nexti.dto.biblioteca.ResponseBibliotecaDTO;
import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.response.ErrorResponse;
import br.edu.fapce.nexti.service.BibliotecaService;
import br.edu.fapce.nexti.util.GenericsUtil;

@RestController
@CrossOrigin(origins = "*")
public class BibliotecaController {

	private static final String BIBLIOTECA = "/bibliotecas";

	private static final String BIBLIOTECABYUSUARIO = "/bibliotecas/{UserId}";

	private static final String SAVEBIBLIOTECA = "/biblioteca";

	// private static final String SAVESEMUSUARIO = "/saveBiblioteca_SemUsuario";

	private static final String DELETEBIBLIOTECA = "/biblioteca/{BibliotecaId}";

	private static final String UPDATEBIBLIOTECA = "/biblioteca";

	@Autowired
	private BibliotecaService bibliotecaService;

	@SuppressWarnings("rawtypes")
	@GetMapping(value = BIBLIOTECA)
	public ResponseEntity findAll() {

		List<Biblioteca> listaBibliotecas = bibliotecaService.findAll();

		List<ResponseBibliotecaDTO> dtoList = new ArrayList<>();
		listaBibliotecas.forEach(bi -> dtoList.add(bi.toBibliotecaDTO()));

		return GenericsUtil.objectToResponse(dtoList);
	}

	@GetMapping(value = BIBLIOTECABYUSUARIO)
	public ResponseEntity<?> findByIdUsuario(@PathVariable(value = "UserId") Long userId) {
		List<Biblioteca> listaBibliotecaByIdUser = bibliotecaService.findByUserId(userId);

		List<ResponseBibliotecaDTO> dtoListByUsuario = new ArrayList<>();
		listaBibliotecaByIdUser.forEach(bi -> dtoListByUsuario.add(bi.toBibliotecaDTO()));

		return GenericsUtil.objectToResponse(dtoListByUsuario);
	}

	@PostMapping(value = SAVEBIBLIOTECA)
	public Biblioteca save(@Valid @RequestBody ResponseBibliotecaComUsuarioDTO dto) {
		return bibliotecaService.save(dto);
	}

	// @RequestMapping(value = SAVESEMUSUARIO, method = POST)
	// public Biblioteca saveSemUsuario(@Valid @RequestBody ResponseBibliotecaDTO
	// dto) {
	// return bibliotecaService.saveSemUsuario(dto);
	// }

	@DeleteMapping(value = DELETEBIBLIOTECA)
	public ResponseEntity<?> delete(@PathVariable(value = "BibliotecaId") Long bibliotecaId) {
		ErrorResponse<String> response = new ErrorResponse<>();
		Optional<Biblioteca> biblioteca = bibliotecaService.findById(bibliotecaId);

		if (biblioteca == null) {
			return ResponseEntity.badRequest().body(response);
		}

		bibliotecaService.delete(bibliotecaId);
		return ResponseEntity.ok(new ErrorResponse<String>());
	}

	@PutMapping(value = UPDATEBIBLIOTECA)
	public ResponseEntity<?> update(@RequestBody Biblioteca biblioteca, BindingResult result) {
		ErrorResponse<Biblioteca> response = new ErrorResponse<>();

		try {
			validateUpdate(biblioteca, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			biblioteca.setNome(biblioteca.getNome());
			bibliotecaService.update(biblioteca);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	private void validateUpdate(Biblioteca biblioteca, BindingResult result) {
		if (biblioteca.getId() == null) {
			result.addError(new ObjectError("Biblioteca", "Id não informado"));
			return;
		}
		if (biblioteca.getUsuario().getId() == null) {
			result.addError(new ObjectError("Biblioteca", "Id do usuario não informado"));
			return;
		}
	}
}
