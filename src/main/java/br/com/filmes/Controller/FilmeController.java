package br.com.filmes.Controller;
import br.com.filmes.entities.Filme;
import br.com.filmes.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //indica pro spring que é uma controller
@RequestMapping("/filmes") //indica pro spring que vamos usar /filmes na rota
public class FilmeController {

    @Autowired //que é isso meu deus do céu
    private FilmeService service;

//POST
    @PostMapping
    public List<Filme> criarFilmesEmLote(@RequestBody List<Filme> listaFilmes) {
        return service.adicionarFilmesEmLote(listaFilmes);
    }

//LISTAR TUDO
    @GetMapping
    public List<Filme> listarFilmes() {
        return service.listarFilmes();
    }

//LISTAR POR ID
    @GetMapping("/{id}")
    public Filme buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id);
    }

//PUT
    @PutMapping("/{id}")
    public Filme atualizarFilme(@PathVariable int id, @RequestBody Filme novoFilme) {
        return service.atualizarFilme(id, novoFilme);
    } //PatVariable passa a URL como parametro, RequestBody pega o Json e transforma pra java

//DELETE
    @DeleteMapping("/{id}")
    public String removerFilme(@PathVariable int id) {
        boolean deletado = service.removerFilme(id);

        if (deletado) {
            return "Filme deletado!";
        } else {
            return "Filme não encontrado.";
        }
    }

}