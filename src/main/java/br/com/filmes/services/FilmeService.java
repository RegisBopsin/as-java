package br.com.filmes.services;

import br.com.filmes.dto.filme.*;
import br.com.filmes.entities.Diretor;
import br.com.filmes.entities.Filme;
import br.com.filmes.repositories.DiretorRepository;
import br.com.filmes.repositories.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final DiretorRepository diretorRepository;

    public List<FilmeResponseDTO> listar() {
        return filmeRepository.findAll()
                .stream()
                .map(f -> {
                    FilmeResponseDTO dto = new FilmeResponseDTO();
                    dto.setId(f.getId());
                    dto.setTitulo(f.getTitulo());
                    dto.setDiretorNome(f.getDiretor().getNome());
                    return dto;
                })
                .toList();
    }

    public FilmeResponseDTO criar(FilmeDTO dto) {
        Diretor diretor = diretorRepository.findById(dto.getDiretorId()).orElseThrow();

        Filme filme = new Filme();
        filme.setTitulo(dto.getTitulo());
        filme.setDiretor(diretor);
        filmeRepository.save(filme);

        FilmeResponseDTO response = new FilmeResponseDTO();
        response.setId(filme.getId());
        response.setTitulo(filme.getTitulo());
        response.setDiretorNome(diretor.getNome());
        return response;
    }

    public void deletar(Long id) {
        filmeRepository.deleteById(id);
    }
}
