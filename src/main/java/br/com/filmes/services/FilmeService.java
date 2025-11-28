package br.com.filmes.services;
import br.com.filmes.entities.Filme;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service //indica pro spring que é service
public class FilmeService {

    private final List<Filme> filmes = new ArrayList<>();

//CREATE
    public List<Filme> adicionarFilmesEmLote(List<Filme> listaFilmes) {
        filmes.addAll(listaFilmes);
        return listaFilmes;
    }

//LISTAGEM DE TODOS
    public List<Filme> listarFilmes() {
        return filmes;
    }

//LISTAGEM POR ID
    public Filme buscarPorId(int id) {
        return filmes.stream()
                .filter(f -> f.getId() == id)
                .findFirst()// retorna só o que passou no filtro por primeiro
                .orElse(null);// caso contrario nao retorna nada
    }

//UPDATE
    public Filme atualizarFilme(int id, Filme novoFilme) {
        for (int i = 0; i < filmes.size(); i++) { //CONTA ATÉ O ID DESEJADO
            if (filmes.get(i).getId() == id) { //PARA NO ID DESEJADO
                filmes.set(i, novoFilme); //AJEITA O ID QUE PAROU
                return novoFilme; //RETORNA O ID ATUALIZADO
            } //SE TA TUDO CERTO PQ TA DANDO PROBLEMA?
        }
        return null;
    }

//DELETE
    public boolean removerFilme(int id) {
        return filmes.removeIf(f -> f.getId() == id);
    }
}
