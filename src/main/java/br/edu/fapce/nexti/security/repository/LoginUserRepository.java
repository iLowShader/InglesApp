package br.edu.fapce.nexti.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.edu.fapce.nexti.security.model.LoginUser;

public interface LoginUserRepository extends CrudRepository<LoginUser, Long>{
	
	LoginUser findByEmail(String email);
	
	void deleteById(Long Id);
	
	Optional<LoginUser> findById(Long loginUserId);
	
}
