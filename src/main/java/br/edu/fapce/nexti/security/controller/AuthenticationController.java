package br.edu.fapce.nexti.security.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fapce.nexti.security.dto.token.LoginDTO;
import br.edu.fapce.nexti.security.model.LoginUser;
import br.edu.fapce.nexti.security.model.LoginUserService;
import br.edu.fapce.nexti.security.utils.JwtTokenUtil;
import br.edu.fapce.nexti.util.GenericsUtil;

@RestController
@RequestMapping("v1/login")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private LoginUserService loginUserService;

	/**
	 * Gera e retorna um novo token JWT.
	 * 
	 * @param authenticationDto
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws AuthenticationException
	 */
	@PostMapping
	@SuppressWarnings("rawtypes")
	public ResponseEntity gerarTokenJwt(@Valid @RequestBody LoginDTO authenticationDto, BindingResult result)
			throws AuthenticationException {

		if (result.hasErrors()) {

			List<String> errors = new ArrayList<>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return GenericsUtil.errorsToResponse(errors);
		}

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationDto.getEmail(), authenticationDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
		String token = jwtTokenUtil.obterToken(userDetails);
		LoginUser loginUser = loginUserService.findByEmail(authenticationDto.getEmail());
		return GenericsUtil.objectToResponse(loginUser.toResponseLoginUserDTOToken(token));
		
	}
	
	

}