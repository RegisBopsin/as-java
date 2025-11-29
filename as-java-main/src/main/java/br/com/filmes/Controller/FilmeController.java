package br.com.filmes.Controller;

import br.com.filmes.dto.filme.*;
import br.com.filmes.services.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
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

    // POST (criar)
    @PostMapping
    public FilmeResponseDTO criar(@RequestBody FilmeDTO dto) {
        return filmeService.criar(dto);
    }

    // PUT (editar)
    @PutMapping("/{id}")
    public FilmeResponseDTO atualizar(@PathVariable Long id, @RequestBody FilmeUpdateDTO dto) {
        return filmeService.atualizar(id, dto);
    }

    // DELETE (remover)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        filmeService.deletar(id);
    }
}