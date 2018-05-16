package br.edu.fapce.nexti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@ComponentScan()
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
		@RequestMapping("/")
		String index() {
			return "index";
		}
}
