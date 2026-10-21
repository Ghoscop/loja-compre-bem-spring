
package com.senai.jonatas.comprebem.service;

import com.senai.jonatas.comprebem.dto.ProdutoRequestDTO;
import com.senai.jonatas.comprebem.dto.ProdutoResponseDTO;
import com.senai.jonatas.comprebem.entity.Produto;
import com.senai.jonatas.comprebem.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Page<ProdutoResponseDTO> findAll(
            String busca, Pageable pageable) {

        Page<Produto> produtos;

        if (busca != null && !busca.isBlank()) {
            produtos = produtoRepository
                    .findByNomeContainingIgnoreCase(
                            busca.trim(), pageable);
        } else {
            produtos = produtoRepository.findAll(pageable);
        }

        return produtos.map(ProdutoResponseDTO::toDto);
    }

    public ProdutoResponseDTO findById(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        return ProdutoResponseDTO.toDto(produto);
    }

    public ProdutoResponseDTO save(ProdutoRequestDTO request) {
        Produto produto = Produto.toEntity(request);

        if (produto.getQuantidade() == null) {
            produto.setQuantidade(0);
        }

        Produto salvo = produtoRepository.save(produto);

        return ProdutoResponseDTO.toDto(salvo);
    }

    public ProdutoResponseDTO update(
            UUID id, ProdutoRequestDTO request) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setCategoria(request.categoria());
        produto.setImagem(request.imagem());

        return ProdutoResponseDTO.toDto(
                produtoRepository.save(produto)
        );
    }

    public void delete(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        produtoRepository.delete(produto);
    }
}
