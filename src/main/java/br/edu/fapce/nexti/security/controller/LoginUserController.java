package br.edu.fapce.nexti.security.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fapce.nexti.security.dto.loginuser.LoginUserDTO;
import br.edu.fapce.nexti.security.model.LoginUser;
import br.edu.fapce.nexti.security.model.LoginUserService;
import br.edu.fapce.nexti.util.GenericsUtil;

@RestController
@CrossOrigin(origins = "*")
public class LoginUserController {
	private static final String REGISTER_USER = "/cadastrar";

	@Autowired
	private LoginUserService loginUserService;

	@SuppressWarnings("rawtypes")
	@PostMapping(value = REGISTER_USER)
	public ResponseEntity save(@Valid @RequestBody LoginUserDTO dto, BindingResult result) {

		if (result.hasErrors()) {

			return GenericsUtil.errorsToResponse(result);
		}

		List<String> errors = loginUserService.errorsToSave(dto);
		if (!errors.isEmpty()) {

			return GenericsUtil.errorsToResponse(errors);
		}

		LoginUser loginUser = loginUserService.save(dto);
		return GenericsUtil.objectToResponse(loginUser.toResponseLoginUserDTO());

	}

}