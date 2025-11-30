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

    // POST (criar) - MENSAGEM DE ERRO que o Diretor não existe
    public FilmeResponseDTO criar(FilmeDTO dto) {
        Diretor diretor = diretorRepository.findById(dto.getDiretorId())
                // MENSAGEM pro requisito
                .orElseThrow(() -> new RuntimeException("Diretor não existe. Não foi possível criar o filme."));

        Filme filme = new Filme();
        filme.setTitulo(dto.getTitulo());
        filme.setDiretor(diretor);
        Filme filmeSalvo = filmeRepository.save(filme);

        return toResponseDTO(filmeSalvo);
    }

    // PUT (editar) - VALIDACAO DE "NAO ENCONTRADO"
    public FilmeResponseDTO atualizar(Long id, FilmeUpdateDTO dto) {
        Filme filme = filmeRepository.findById(id)
                // MENSAGEM para "não encontrado para atualizar"
                .orElseThrow(() -> new RuntimeException("Filme não encontrado para atualização!"));

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

    // DELETE (remover) - MENSAGEM DE SUCESSO E VALIDACAO DE "NAO ENCONTRADO"
    public String deletar(Long id) { // <-- mudar o retorno de void pra string
        Filme filme = filmeRepository.findById(id)
                // MENSAGEM pra nao encontrado para deletar
                .orElseThrow(() -> new RuntimeException("Filme não encontrado para exclusão!"));

        String tituloFilme = filme.getTitulo();
        filmeRepository.delete(filme);

        // MENSAGEM de retorno de sucesso fama e gritaria
        return "Filme '" + tituloFilme + "' excluído com sucesso.";
    }
}