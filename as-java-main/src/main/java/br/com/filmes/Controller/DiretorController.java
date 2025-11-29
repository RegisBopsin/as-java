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

import java.util.List;

@RestController
@RequestMapping("/diretores")
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

    // PUT (editar)
    @PutMapping("/{id}")
    public DiretorResponseDTO atualizar(@PathVariable Long id, @RequestBody DiretorDTO dto) {
        return diretorService.atualizar(id, dto);
    }

    // DELETE (remover)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        diretorService.deletar(id);
    }
}