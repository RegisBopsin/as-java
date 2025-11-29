package br.com.filmes.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Diretor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "diretor", cascade = CascadeType.ALL)
    private List<Filme> filmes;
}
