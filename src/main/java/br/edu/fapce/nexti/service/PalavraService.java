package br.edu.fapce.nexti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fapce.nexti.dto.palavra.ResponsePalavraComBibliotecaDTO;
import br.edu.fapce.nexti.dto.palavra.ResponsePalavraDTO;
import br.edu.fapce.nexti.model.PalavrasBiblioteca;
import br.edu.fapce.nexti.repository.PalavrasBibliotecaRepository;

@Service
public class PalavraService {

	@Autowired
	private PalavrasBibliotecaRepository palavrasRepository;

	public List<PalavrasBiblioteca> findAll() {
		return palavrasRepository.findAll();
	}

	public List<PalavrasBiblioteca> findByBibliotecaId(Long idBiblioteca) {
		return palavrasRepository.findByBibliotecaId(idBiblioteca);
	}

	public Optional<PalavrasBiblioteca> findById(Long id) {
		return palavrasRepository.findById(id);
	}

	public PalavrasBiblioteca findByNome(String nome) {
		return palavrasRepository.findByNome(nome);
	}

	public PalavrasBiblioteca save(ResponsePalavraComBibliotecaDTO dto) {
		PalavrasBiblioteca palavra = palavraFromPalavraDTO(dto);
		return palavrasRepository.save(palavra);
	}

	public PalavrasBiblioteca saveSemBiblioteca(ResponsePalavraDTO dto) {
		PalavrasBiblioteca palavra = palavraSemBibliotecaFromPalavraDTO(dto);
		return palavrasRepository.save(palavra);
	}

	public PalavrasBiblioteca update(PalavrasBiblioteca palavra) {
		return palavrasRepository.save(palavra);
	}
	
	public PalavrasBiblioteca palavraFromPalavraDTO(ResponsePalavraComBibliotecaDTO dto) {
		return PalavrasBiblioteca.builder().nome(dto.getNome()).biblioteca(dto.getBiblioteca())
				.traducao1(dto.getTraducao1()).traducao2(dto.getTraducao2()).build();
	}

	public PalavrasBiblioteca palavraSemBibliotecaFromPalavraDTO(ResponsePalavraDTO dto) {
		return PalavrasBiblioteca.builder().nome(dto.getNome()).traducao1(dto.getTraducao1())
				.traducao2(dto.getTraducao2()).build();
	}

	public void delete(Long id) {
		palavrasRepository.deleteById(id);
	}
}
