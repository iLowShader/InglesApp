package br.edu.fapce.nexti;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fapce.nexti.model.Usuario;
import br.edu.fapce.nexti.service.UsuarioService;


@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	private static final String USUARIO = "/v1/usuario";

	@Autowired
	private UsuarioService usuarioService;
	
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value = USUARIO, method = GET)
	public ResponseEntity findAll() {

		List<Usuario> finalClientList = usuarioService.findAll();

		List<ConsumerFinalClientDTO> dtoList = new ArrayList<>();
		finalClientList.forEach(fc -> dtoList.add(fc.toConsumerFinalClientDTO()));

		return GenericsUtil.objectToResponse(dtoList);

	}
	
}
