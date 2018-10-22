package br.edu.fapce.nexti.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fapce.nexti.dto.biblioteca.ResponseBibliotecaComUsuarioDTO;
import br.edu.fapce.nexti.dto.biblioteca.ResponseBibliotecaDTO;
import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.repository.BibliotecaRepository;
import br.edu.fapce.nexti.service.BibliotecaService;
import br.edu.fapce.nexti.util.GenericsUtil;

@RestController
@CrossOrigin(origins = "*")
public class BibliotecaController {

	private static final String BIBLIOTECA = "/v2/bibliotecas";

	private static final String BIBLIOTECABYUSUARIO = "/bibliotecasFindByUser/{UserId}";

	private static final String SAVEBIBLIOTECA = "/saveBiblioteca";

	private static final String DELETEBIBLIOTECA = "/deleteBiblioteca/{BibliotecaId}";

	@Autowired
	private BibliotecaService bibliotecaService;

	private BibliotecaRepository bibliotecaRepository;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = BIBLIOTECA, method = GET)
	public ResponseEntity findAll() {

		List<Biblioteca> listaBibliotecas = bibliotecaService.findAll();

		List<ResponseBibliotecaDTO> dtoList = new ArrayList<>();
		listaBibliotecas.forEach(bi -> dtoList.add(bi.toBibliotecaDTO()));

		return GenericsUtil.objectToResponse(dtoList);
	}

	// @SuppressWarnings("rawtypes")
	// @RequestMapping(value = "/bibi", method = POST)
	// public ResponseEntity findByUsuario(@Valid @RequestBody String email) {
	//
	// // System.out.println(usuario.getEmail()
	// // +"\n"+usuario.getUserRole()+
	// // "\n"+usuario.getPassword());
	//
	// List<Biblioteca> listaBibliotecasByUsuario =
	// bibliotecaService.findAllByEmail(email);
	//
	// List<ResponseBibliotecaDTO> dtoListByUsuario = new ArrayList<>();
	// listaBibliotecasByUsuario.forEach(bi ->
	// dtoListByUsuario.add(bi.toBibliotecaDTO()));
	//
	// return GenericsUtil.objectToResponse(dtoListByUsuario);
	// }

	@RequestMapping(value = BIBLIOTECABYUSUARIO, method = GET)
	public ResponseEntity findByIdUsuario(@PathVariable(value = "UserId") Long userId) {
		List<Biblioteca> listaBibliotecaByIdUser = bibliotecaService.findByUserId(userId);

		List<ResponseBibliotecaDTO> dtoListByUsuario = new ArrayList<>();
		listaBibliotecaByIdUser.forEach(bi -> dtoListByUsuario.add(bi.toBibliotecaDTO()));

		return GenericsUtil.objectToResponse(dtoListByUsuario);
	}

	@RequestMapping(value = SAVEBIBLIOTECA, method = POST)
	public Biblioteca save(@Valid @RequestBody ResponseBibliotecaComUsuarioDTO dto) {
		return bibliotecaService.save(dto);
	}

	@DeleteMapping(value = DELETEBIBLIOTECA)
	public ResponseEntity<Biblioteca> delete(@PathVariable(value = "BibliotecaId") Long bibliotecaId) {
		Biblioteca biblioteca = bibliotecaService.findById(bibliotecaId);
		
		if (biblioteca == null) {
			System.out.println("Não foi possível deletar. Biblioteca com id " + bibliotecaId + " não encontrada.");
			return new ResponseEntity<Biblioteca>(HttpStatus.NOT_FOUND);
		}

		bibliotecaService.delete(bibliotecaId);
		return new ResponseEntity<Biblioteca>(HttpStatus.OK);
	}
}
