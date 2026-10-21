
package com.senai.jonatas.comprebem.entity;

import com.senai.jonatas.comprebem.dto.ProdutoRequestDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column
    private String categoria;

    @Column(length = 500)
    private String descricao;

    @Column
    private String imagem;

    @Column(nullable = false)
    private Integer quantidade;

    public static Produto toEntity(ProdutoRequestDTO request) {
        Produto produto = new Produto();

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setCategoria(request.categoria());
        produto.setImagem(request.imagem());
        produto.setQuantidade(0);

        return produto;
    }
}
