// br.com.filmes.dto.filme/FilmeUpdateDTO.java

package br.com.filmes.dto.filme;

import lombok.Data;

@Data
public class FilmeUpdateDTO {

    //  atualizar o título.
    private String titulo;

    // trocar o Diretor (relacionamento).
    private Long novoDiretorId;
}
