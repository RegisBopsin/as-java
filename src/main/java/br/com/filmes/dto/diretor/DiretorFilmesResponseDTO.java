package br.com.filmes.dto.diretor;

import br.com.filmes.dto.filme.FilmeResponseDTO;
import lombok.Data;
import java.util.List;

@Data
public class DiretorFilmesResponseDTO {
    private Long id;
    private String nome;
    private List<FilmeResponseDTO> filmes;
}
