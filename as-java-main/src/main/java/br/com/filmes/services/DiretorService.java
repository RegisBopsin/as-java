package br.com.filmes.services;

import br.com.filmes.dto.diretor.*;
import br.com.filmes.dto.filme.FilmeResponseDTO;
import br.com.filmes.entities.Diretor;
import br.com.filmes.repositories.DiretorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DiretorService {

    private final DiretorRepository diretorRepository;

    public List<DiretorResponseDTO> listar() {
        return diretorRepository.findAll()
                .stream()
                .map(d -> {
                    DiretorResponseDTO dto = new DiretorResponseDTO();
                    dto.setId(d.getId());
                    dto.setNome(d.getNome());
                    return dto;
                })
                .toList();
    }

    public DiretorFilmesResponseDTO buscarPorId(Long id) {
        Diretor d = diretorRepository.findById(id).orElseThrow();

        DiretorFilmesResponseDTO dto = new DiretorFilmesResponseDTO();
        dto.setId(d.getId());
        dto.setNome(d.getNome());

        // mapeamento do lado Many para o DTO de resposta (FilmeResponseDTO)
        dto.setFilmes(
                d.getFilmes().stream().map(f -> {
                    FilmeResponseDTO fr = new FilmeResponseDTO();
                    fr.setId(f.getId());
                    fr.setTitulo(f.getTitulo());
                    // busca o nome do diretor da propria entidade Diretor (d) para o DTO do Filme
                    fr.setDiretorNome(d.getNome());
                    return fr;
                }).collect(Collectors.toList())
        );

        return dto;
    }

    public DiretorResponseDTO criar(DiretorDTO dto) {
        Diretor d = new Diretor();
        d.setNome(dto.getNome());
        diretorRepository.save(d);

        DiretorResponseDTO response = new DiretorResponseDTO();
        response.setId(d.getId());
        response.setNome(d.getNome());
        return response;
    }

    // PUT (editar)
    public DiretorResponseDTO atualizar(Long id, DiretorDTO dto) {
        Diretor diretor = diretorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diretor não encontrado para atualizar!"));

        // atualiza o nome se for passado (protegendo contra null)
        if (dto.getNome() != null) {
            diretor.setNome(dto.getNome());
        }

        Diretor diretorAtualizado = diretorRepository.save(diretor);

        DiretorResponseDTO response = new DiretorResponseDTO();
        response.setId(diretorAtualizado.getId());
        response.setNome(diretorAtualizado.getNome());
        return response;
    }

    // DELETE (remover)
    public void deletar(Long id) {
        diretorRepository.deleteById(id);
    }
}