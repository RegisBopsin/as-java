package br.com.filmes.Controller;

import br.com.filmes.dto.diretor.DiretorDTO;
import br.com.filmes.dto.diretor.DiretorFilmesResponseDTO;
import br.com.filmes.dto.diretor.DiretorResponseDTO;
import br.com.filmes.services.DiretorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/diretor")
@RequiredArgsConstructor
public class DiretorController {

    private final DiretorService diretorService;

    // GET (listar)
    @GetMapping
    public List<DiretorResponseDTO> listar() {
        return diretorService.listar();
    }

    // GET (buscar por id) - Retorna o diretor e a lista de filmes
    @GetMapping("/{id}")
    public DiretorFilmesResponseDTO buscarPorId(@PathVariable Long id) {
        return diretorService.buscarPorId(id);
    }

    // POST (criar)
    @PostMapping
    public DiretorResponseDTO criar(@RequestBody DiretorDTO dto) {
        return diretorService.criar(dto);
    }

    // PUT (editar) - pra resolver o erro de diretor não encontrado (404 Not Found)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody DiretorDTO dto) {
        try {
            DiretorResponseDTO response = diretorService.atualizar(id, dto);
            // to retornando o recurso atualizado com status 200 OK
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // CASO E SE o diretor não for encontrado, vai retorna a mensagem com status 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE (remover) - vai dar mensagem de sucesso (200 OK) ou erro (404 Not Found)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) { // mais uma vez mano vamo mudar o retorno para ResponseEntity<String>
        try {
            String mensagem = diretorService.deletar(id);
            return ResponseEntity.ok(mensagem); // 200 OK com a mensagem de sucesso
        } catch (RuntimeException e) {
            // se não encontrar, retorna 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}