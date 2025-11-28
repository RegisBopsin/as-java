package br.com.filmes.controllers;

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

    @GetMapping
    public List<FilmeResponseDTO> listar() {
        return filmeService.listar();
    }

    @PostMapping
    public FilmeResponseDTO criar(@RequestBody FilmeDTO dto) {
        return filmeService.criar(dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        filmeService.deletar(id);
    }
}
