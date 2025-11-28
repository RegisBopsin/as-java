package br.com.filmes.dto.filme;

import lombok.Data;

@Data
public class FilmeResponseDTO {
    private Long id;
    private String titulo;
    private String diretorNome;
}
