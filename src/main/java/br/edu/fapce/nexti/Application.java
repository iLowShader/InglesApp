package br.edu.fapce.nexti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import br.edu.fapce.nexti.model.Biblioteca;
import br.edu.fapce.nexti.model.PalavrasBiblioteca;
import br.edu.fapce.nexti.security.model.LoginUser;
import br.edu.fapce.nexti.security.model.LoginUserService;
import br.edu.fapce.nexti.security.model.UserRole;
import br.edu.fapce.nexti.service.BibliotecaService;
import br.edu.fapce.nexti.service.PalavraService;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Autowired
	LoginUserService loginUserService;

	@Autowired
	BibliotecaService bibliotecaService;

	@Autowired
	PalavraService palavraService;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			LoginUser loginUser = createDefaultFinalUser();
//			Biblioteca biblioteca = createDefaultBiblioteca(loginUser);
//			createDefaultBiblioteca2(loginUser);
//			createDefaultPalavra(biblioteca);
		};
	}

	private LoginUser createDefaultFinalUser() {
		LoginUser loginUser = loginUserService.findByEmail("defaultfinaluser@gmail.com");
		if (loginUser == null) {
			LoginUser loginUser1 = LoginUser.builder().email("defaultfinaluser@gmail.com").password("123")
					.userRole(UserRole.ROLE_USUARIO).nome("LoginUserDefault").build();
			loginUser1=loginUserService.save(loginUser1);
			return loginUser1;
		}
		return loginUser;
	}

//	private Biblioteca createDefaultBiblioteca(LoginUser loginUser) {
//		Biblioteca biblioteca = bibliotecaService.findByNome("Biblioteca1");
//		if (biblioteca == null) {
//			Biblioteca biblioteca1 = Biblioteca.builder().nome("Biblioteca1").usuario(loginUser).build();
//			biblioteca1 =bibliotecaService.save(biblioteca1);
//			return biblioteca1;
//		}
//		return biblioteca;
//	}
//	
//	private Biblioteca createDefaultBiblioteca2(LoginUser loginUser) {
//		Biblioteca biblioteca = bibliotecaService.findByNome("Biblioteca2");
//		if (biblioteca == null) {
//			Biblioteca biblioteca1 = Biblioteca.builder().nome("Biblioteca2").usuario(loginUser).build();
//			biblioteca1 =bibliotecaService.save(biblioteca1);
//			return biblioteca1;
//		}
//		return biblioteca;
//	}
//
//	private PalavrasBiblioteca createDefaultPalavra(Biblioteca bi) {
//		PalavrasBiblioteca palavra = palavraService.findByNome("Car");
//		if (palavra == null) {
//			PalavrasBiblioteca palavra1 = PalavrasBiblioteca.builder().nome("Car").traducao1("Carro").biblioteca(bi)
//					.build();
//			palavraService.save(palavra1);
//			return palavra1;
//		}
//		return palavra;
//	}

}
