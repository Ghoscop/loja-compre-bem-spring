
package com.senai.jonatas.comprebem.controller;

import com.senai.jonatas.comprebem.dto.ProdutoRequestDTO;
import com.senai.jonatas.comprebem.dto.ProdutoResponseDTO;
import com.senai.jonatas.comprebem.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public Page<ProdutoResponseDTO> findAll(
            @RequestParam(required = false) String busca,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        if (page < 0) {
            throw new IllegalArgumentException(
                    "A página não pode ser negativa");
        }

        if (size < 1) {
            throw new IllegalArgumentException(
                    "O tamanho deve ser pelo menos 1");
        }

        size = Math.min(size, 50);

        return produtoService.findAll(
                busca,
                PageRequest.of(page, size)
        );
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO findById(@PathVariable UUID id) {
        return produtoService.findById(id);
    }

    @PostMapping
    public ProdutoResponseDTO save(
            @Valid @RequestBody ProdutoRequestDTO produto) {
        return produtoService.save(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoResponseDTO update(
            @PathVariable UUID produtoId,
            @Valid @RequestBody ProdutoRequestDTO produto) {
        return produtoService.update(produtoId, produto);
    }

    @DeleteMapping("/{produtoId}")
    public void delete(@PathVariable UUID produtoId) {
        produtoService.delete(produtoId);
    }
}
