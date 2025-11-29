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

    // converte a entidade Filme para o DTO de resposta
    private FilmeResponseDTO toResponseDTO(Filme filme) {
        FilmeResponseDTO dto = new FilmeResponseDTO();
        dto.setId(filme.getId());
        dto.setTitulo(filme.getTitulo());
        // trata o caso onde o diretor pode ser null (MAS NAO PODE SER NULL AQUI MANO)
        dto.setDiretorNome(filme.getDiretor() != null ? filme.getDiretor().getNome() : "Sem Diretor");
        return dto;
    }

    // GET (listar)
    public List<FilmeResponseDTO> listar() {
        return filmeRepository.findAll()
                .stream()
                .map(this::toResponseDTO) // reutiliza o metodo de conversão
                .toList();
    }

    // GET (buscar por id) e resolve o erro no controller
    public FilmeResponseDTO buscarPorId(Long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado!"));
        return toResponseDTO(filme);
    }

    // POST (criar)
    public FilmeResponseDTO criar(FilmeDTO dto) {
        Diretor diretor = diretorRepository.findById(dto.getDiretorId())
                .orElseThrow(() -> new RuntimeException("Diretor não encontrado!"));

        Filme filme = new Filme();
        filme.setTitulo(dto.getTitulo());
        filme.setDiretor(diretor);
        Filme filmeSalvo = filmeRepository.save(filme);

        return toResponseDTO(filmeSalvo);
    }

    // PUT (editar) e resolve o erro no controller
    public FilmeResponseDTO atualizar(Long id, FilmeUpdateDTO dto) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado para atualizar!"));

        // att o Título (se for passado ne)
        if (dto.getTitulo() != null) {
            filme.setTitulo(dto.getTitulo());
        }

        // att o Diretor (se passado)
        if (dto.getNovoDiretorId() != null) {
            Diretor novoDiretor = diretorRepository.findById(dto.getNovoDiretorId())
                    .orElseThrow(() -> new RuntimeException("Novo Diretor não encontrado!"));
            filme.setDiretor(novoDiretor);
        }

        Filme filmeAtualizado = filmeRepository.save(filme);

        return toResponseDTO(filmeAtualizado);
    }

    // DELETE (remover)
    public void deletar(Long id) {
        // opção 1: só deleta, o JPA trata as chaves estrangeiras
        filmeRepository.deleteById(id);

        // ppção 2 (mais topzera): ve se existe antes de deletar
        // if (!filmeRepository.existsById(id)) {
        //     throw new RuntimeException("Filme não encontrado para deletar!");
        // }
        // filmeRepository.deleteById(id);
    }
}