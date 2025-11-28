package br.com.filmes.repositories;

import br.com.filmes.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
