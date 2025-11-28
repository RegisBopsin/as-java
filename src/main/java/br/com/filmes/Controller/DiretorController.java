package br.com.filmes.controllers;

import br.com.filmes.dto.diretor.*;
import br.com.filmes.services.DiretorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diretores")
@RequiredArgsConstructor
public class DiretorController {

    private final DiretorService diretorService;

    @GetMapping
    public List<DiretorResponseDTO> listar() {
        return diretorService.listar();
    }

    @GetMapping("/{id}")
    public DiretorFilmesResponseDTO buscarPorId(@PathVariable Long id) {
        return diretorService.buscarPorId(id);
    }

    @PostMapping
    public DiretorResponseDTO criar(@RequestBody DiretorDTO dto) {
        return diretorService.criar(dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        diretorService.deletar(id);
    }
}
