package br.com.filmes.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "diretor_id")
    private Diretor diretor;
}
