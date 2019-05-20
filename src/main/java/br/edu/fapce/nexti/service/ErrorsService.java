package br.edu.fapce.nexti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fapce.nexti.controller.validator.MyValidator;
import br.edu.fapce.nexti.security.model.LoginUser;
import br.edu.fapce.nexti.security.model.LoginUserService;

@Service
public class ErrorsService {
	
	@Autowired
	private LoginUserService loginUserService;
	
	public void addErrorsIfLoginUserByIdNotExist(Long loginUserId, List<String> errors) {
		String errorMessage = "nao existe loginUser com o id [" + loginUserId + "]";
		Optional<LoginUser> loginUser = loginUserService.findById(loginUserId);
		MyValidator.addErrorsWhenNull(errors, errorMessage, loginUser);
	}
	
	public void addErrorsIfLoginUserByEmailExist(String loginUserEmail, List<String> errors) {
		String errorMessage = "Ja existe loginUser com o email [" + loginUserEmail + "]";
		LoginUser loginUser = loginUserService.findByEmail(loginUserEmail);
		MyValidator.addErrorsWhenNotNull(errors, errorMessage, loginUser);
	}
}
