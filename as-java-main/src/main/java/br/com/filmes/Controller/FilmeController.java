package br.com.filmes.Controller;

import br.com.filmes.dto.filme.*;
import br.com.filmes.services.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/filme")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;

    // GET (listar)
    @GetMapping
    public List<FilmeResponseDTO> listar() {
        return filmeService.listar();
    }

    // GET (buscar por id)
    @GetMapping("/{id}")
    public FilmeResponseDTO buscarPorId(@PathVariable Long id) {
        return filmeService.buscarPorId(id);
    }

    // POST (criar) - to resolvendo o erro de diretor que nao existe (400 Bad Request)
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody FilmeDTO dto) {
        try {
            FilmeResponseDTO response = filmeService.criar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 Created
        } catch (RuntimeException e) {
            // caso ne o diretor não existir, retorna a mensagem com status 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT (editar) - pra resolver o erro de filme não encontrado (404 Not Found)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody FilmeUpdateDTO dto) {
        try {
            FilmeResponseDTO response = filmeService.atualizar(id, dto);
            // retorna atualizado com status 200 OK (confirmacao implicita)
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // se o filme ou o novo diretor não for encontrado, retorna a mensagem com status 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE (remover) - da mensagem de sucesso (200 OK) ou erro (404 Not Found)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) { // MUDAR o retorno para ResponseEntity<String>
        try {
            String mensagem = filmeService.deletar(id);
            return ResponseEntity.ok(mensagem); // 200 OK com a msg de sucesso
        } catch (RuntimeException e) {
            // se nnao encontrar vai retornra 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}